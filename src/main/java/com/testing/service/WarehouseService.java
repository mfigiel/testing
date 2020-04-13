package com.testing.service;

import com.google.gson.Gson;
import com.testing.api.integration.ClientServiceClient;
import com.testing.api.integration.Order.OrderServiceClient;
import com.testing.api.integration.WarehouseClient;
import com.testing.api.resource.ProductApi;
import com.testing.api.resource.ProductState;
import com.testing.api.resource.Transaction;
import com.testing.metrics.Metrics;
import com.testing.service.metrics.CounterService;
import io.micrometer.core.instrument.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseClient warehouseClient;
    private final OrderServiceClient orderClient;
    private final ClientServiceClient clientService;
    private final CounterService counterService;

    public Transaction finishShopTransaction(Transaction transaction) {
        Gson gson = new Gson();
        Transaction copiedTransaction = gson.fromJson(gson.toJson(transaction), Transaction.class);
        if (buyProducts(transaction)) {
            transaction.setClient(clientService.addClient(transaction.getClient()));
            transaction.getOrder().setClientId(transaction.getClient().getId());
            transaction.getOrder().setId(orderClient.addOrder(transaction.getOrder()));
            transaction.setFinished(true);
            return verifyTransaction(transaction) == true ? transaction : copiedTransaction;
        }
        return transaction;
    }

    private boolean buyProducts(Transaction transaction) {
        boolean transactionCorrect = true;
        for (ProductApi productApi : transaction.getOrder().getProducts()) {
            try {
                productApi = warehouseClient.buyProduct(productApi.getId());
            } catch (HttpClientErrorException.Conflict e) {
                productApi.setState(ProductState.BOUGHT);
                transactionCorrect = false;
            } catch (HttpClientErrorException.NotFound e) {
                productApi.setState(ProductState.NOT_FOOUND);
                transactionCorrect = false;
            }
        }
        return transactionCorrect;
    }

    private boolean verifyTransaction(Transaction transaction) {
        if (transaction.getClient() != null && transaction.getOrder() != null
                && transaction.getOrder().getProducts() != null &&
                transaction.getClient().getId() != null &&
                transaction.getOrder().getProducts().stream().noneMatch(productApi -> productApi == null)) {
            return true;
        }
        counterService.increment(Metrics.TRANSACTION_BROKEN,
                Tags.of(Metrics.Tags.ORDER_ID, String.valueOf(transaction.getOrder().getId())));

        return false;
    }

    public ProductApi getProduct(long id) {
        counterService.increment(Metrics.PRODUCT_SOLD,
                Tags.of(Metrics.Tags.ID, String.valueOf(id)));
        return warehouseClient.getProduct(id);
    }

    public void addProduct(ProductApi product) {
        counterService.increment(Metrics.ADD_PRODUCT);
        warehouseClient.addProduct(product);
    }

    public List<ProductApi> getProducts() {
        counterService.increment(Metrics.GET_PRODUCTS);
        return warehouseClient.getProducts();
    }
}
