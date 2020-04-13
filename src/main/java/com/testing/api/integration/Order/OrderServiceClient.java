package com.testing.api.integration.Order;

import com.testing.api.mapping.OrderApiOrderMapperImpl;
import com.testing.api.resource.OrderApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class OrderServiceClient {

    public static final String WAREHOUSE_ORDER_ADDRESS = "http://localhost:8082";
    WebClient order = WebClient.create(WAREHOUSE_ORDER_ADDRESS);

    public OrderApi getOrder(Long id){
        return new RestTemplate().getForObject(WAREHOUSE_ORDER_ADDRESS + "/order/" + id, OrderApi.class);
    }

    public List<OrderApi> getOrders(){
        return new RestTemplate().exchange(WAREHOUSE_ORDER_ADDRESS + "/orders",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderApi>>() {
                        }).getBody();
    }

    public Long addOrder(OrderApi newOrder){
        return new RestTemplate().postForObject(WAREHOUSE_ORDER_ADDRESS + "/orders"
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
