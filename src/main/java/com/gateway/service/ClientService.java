package com.gateway.service;

import com.gateway.api.integration.ClientServiceClient;
import com.gateway.api.resource.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientServiceClient clientService;

    public void addClientToTransaction(Transaction transaction) {
        transaction.setClient(clientService.addClient(transaction.getClient()));
    }
}
