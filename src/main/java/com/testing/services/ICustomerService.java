package com.testing.services;

import com.packt.webstore.domain.Customer;

import java.util.List;

public interface ICustomerService {
    Integer addCustomer(Customer var1);

    List<Customer> getCustomersList();
}
