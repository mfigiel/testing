package com.testing.api.integration.Order;

import com.testing.api.mapping.OrderApiOrderMapperImpl;
import com.testing.api.resource.OrderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OrderServiceClient {

    public static final String WAREHOUSE_ORDER_ADDRESS = "http://orders";
    public static final String SINGLE_ORDER_ENDPOINT = "/order/";
    public static final String ALL_ORDERS_ENDPOINT = "/orders";
    public static final String ADD_ORDER_ENDPOINT = "/orders";

    @Autowired
    private RestTemplate loadBalancedRestTemplate;

    public OrderApi getOrder(Long id){
        return loadBalancedRestTemplate.getForObject(WAREHOUSE_ORDER_ADDRESS + SINGLE_ORDER_ENDPOINT + id, OrderApi.class);
    }

    public List<OrderApi> getOrders(){
        return loadBalancedRestTemplate.exchange(WAREHOUSE_ORDER_ADDRESS + ALL_ORDERS_ENDPOINT,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderApi>>() {
                        }).getBody();
    }

    public Long addOrder(OrderApi newOrder){
        return loadBalancedRestTemplate.postForObject(WAREHOUSE_ORDER_ADDRESS + ADD_ORDER_ENDPOINT
                , new HttpEntity<>(prepareOrderRequest(newOrder)), OrderDto.class).getId();

    }

    private OrderDto prepareOrderRequest(OrderApi order) {
        OrderDto orderDto = new OrderApiOrderMapperImpl().orderApiToOrderDto(order);
        order.getProducts()
                .stream()
                .forEach(e -> orderDto.getProducts().add(e.getId().toString()));
        return orderDto;
    }

}
