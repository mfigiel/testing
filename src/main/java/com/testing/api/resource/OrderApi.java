package com.testing.api.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderApi {

    private long id;

    private List<ProductApi> products;

    private Integer clientId;

    private Date orderDate;
}
