package com.testing.api.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductApi {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal unitPrice;
    private String description;
    private String category;
    private Long unitsInStock;
    private Long unitsInOrder;
    private ProductState state = ProductState.NONE;
}
