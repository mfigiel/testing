package com.gateway.api.integration.Warehouse;

import com.gateway.api.resource.ProductApi;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class BuyProductsResponse {
    public List<ProductApi> products = new ArrayList<>();
}
