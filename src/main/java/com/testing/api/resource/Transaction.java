package com.testing.api.resource;

import com.testing.api.validation.ClientValidation;
import com.testing.api.validation.OrderValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @OrderValidation
    OrderApi order;
    @ClientValidation
    ClientApi client;
    boolean finished;
}
