package com.gateway.service;

import com.gateway.api.resource.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final WarehouseService warehouseService;
    private final OrderService orderService;
    private final ClientService clientService;

    public Transaction finishShopTransaction(Transaction transaction) {
        warehouseService.addProductsToTransaction(transaction, warehouseService.buyProducts(transaction));
        if (warehouseService.checkIfAllProductsWasBought(transaction)) {
            clientService.addClientToTransaction(transaction);
            orderService.addOrderToTransaction(transaction);
            setTransactionStatusToFinish(transaction);
        }
        return transaction;
    }

    private void setTransactionStatusToFinish(Transaction transaction) {
        transaction.setFinished(true);
    }
}
