package com.testing.service;

import com.testing.api.integration.ClientServiceClient;
import com.testing.api.integration.OrderServiceClient;
import com.testing.api.integration.WarehouseClient;
import com.testing.api.resource.ProductApi;
import com.testing.api.resource.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @Autowired
    WarehouseClient warehouseClient;

    @Autowired
    OrderServiceClient orderClient;

    @Autowired
    ClientServiceClient clientService;

    public void finishShopTransaction(Transaction transaction) {
        for (ProductApi productApi : transaction.getOrder().getProducts()) {
            warehouseClient.buyProduct(productApi.getId());
        }
        orderClient.addOrder(transaction.getOrder());
        clientService.addClient(transaction.getClient());
    }
}
