package com.gateway.api.integration.Warehouse;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class BuyProductsRequest {
    List<Long> productsId;
}
