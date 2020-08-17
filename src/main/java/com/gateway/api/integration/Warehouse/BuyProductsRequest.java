package com.gateway.api.integration.Warehouse;

import lombok.Data;

import java.util.List;

@Data
public class BuyProductsRequest {
    private List<Long> productsId;

    public BuyProductsRequest() {}

    public BuyProductsRequest(List<Long> products) {
        this.productsId = products;
    }
}
