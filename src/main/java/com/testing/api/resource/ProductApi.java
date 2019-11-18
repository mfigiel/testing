package com.testing.api.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductApi {

    @NotNull
    private long id;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal unitPrice;
    private String description;
    private String category;
    private Long unitsInStock;
    private Long unitsInOrder;
}
