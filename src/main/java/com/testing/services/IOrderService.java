package com.testing.services;

import com.packt.webstore.domain.Order;

import java.util.List;

public interface IOrderService {

    void addOrder(Order var1);

    List<Order> getOrdersList();

    Order getOrderById(String var1);
}
