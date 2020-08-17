package com.gateway.api.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductApi {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal unitPrice;
    @NotNull
    private String description;
    @NotNull
    private String category;
    private Long unitsInStock;
    private Long unitsInOrder;
    private ProductState state = ProductState.NONE;
}
