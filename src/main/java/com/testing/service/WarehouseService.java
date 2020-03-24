package com.testing.service;

import com.testing.api.integration.ClientServiceClient;
import com.testing.api.integration.OrderServiceClient;
import com.testing.api.integration.WarehouseClient;
import com.testing.api.resource.ProductApi;
import com.testing.api.resource.Transaction;
import com.testing.metrics.Metrics;
import com.testing.service.metrics.CounterService;
import io.micrometer.core.instrument.Tags;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    WarehouseClient warehouseClient;

    @Autowired
    OrderServiceClient orderClient;

    @Autowired
    ClientServiceClient clientService;

    @Autowired
    CounterService counterService;

    public Transaction finishShopTransaction(Transaction transaction) {
        Transaction copiedTransaction = (Transaction) SerializationUtils.clone(transaction);
        for (ProductApi productApi : transaction.getOrder().getProducts()) {
            productApi = warehouseClient.buyProduct(productApi.getId());
        }
        transaction.setOrder(orderClient.addOrder(transaction.getOrder()));
        transaction.setClient(clientService.addClient(transaction.getClient()));
        transaction.setFinished(true);
        return verifyTransaction(transaction) == true ? transaction : copiedTransaction;
    }

    private boolean verifyTransaction(Transaction transaction) {
        if (transaction.getClient() == null && transaction.getOrder() == null
                && transaction.getOrder().getProducts() != null &&
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
