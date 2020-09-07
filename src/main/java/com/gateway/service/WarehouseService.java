package com.gateway.service;

import com.gateway.api.integration.ClientServiceClient;
import com.gateway.api.integration.Order.OrderServiceClient;
import com.gateway.api.integration.Warehouse.BuyProductsRequest;
import com.gateway.api.integration.Warehouse.BuyProductsResponse;
import com.gateway.api.integration.Warehouse.WarehouseClient;
import com.gateway.api.resource.ProductApi;
import com.gateway.api.resource.ProductState;
import com.gateway.api.resource.Transaction;
import com.gateway.metrics.Metrics;
import com.gateway.service.metrics.CounterService;
import io.micrometer.core.instrument.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseClient warehouseClient;
    private final OrderServiceClient orderClient;
    private final ClientServiceClient clientService;
    private final CounterService counterService;

    public Transaction finishShopTransaction(Transaction transaction) {
        BuyProductsResponse buyProductsResponse = buyProducts(transaction);
        transaction.getOrder().setProducts(buyProductsResponse.getProducts());
        if (checkIfAllProductsWasBought(transaction)) {
            transaction.setClient(clientService.addClient(transaction.getClient()));
            transaction.getOrder().setClientId(transaction.getClient().getId());
            transaction.getOrder().setId(orderClient.addOrder(transaction.getOrder()));
            transaction.setFinished(true);
        }
        return transaction;
    }

    private boolean checkIfAllProductsWasBought(Transaction transaction) {
        return transaction.getOrder().getProducts().stream().allMatch(product -> product.getState().equals(ProductState.BOUGHT));
    }

    private BuyProductsResponse buyProducts(Transaction transaction) {
        List<Long> productsId = transaction.getOrder().getProducts().stream().map(products -> products.getId()).collect(Collectors.toList());
        return warehouseClient.buyProduct(BuyProductsRequest.builder().productsId(productsId).build());
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
