package com.testing.api.resource;

import com.testing.api.validation.ClientValidation;
import com.testing.api.validation.OrderValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {
    @OrderValidation
    OrderApi order;
    @ClientValidation
    ClientApi client;
    boolean finished;
}
