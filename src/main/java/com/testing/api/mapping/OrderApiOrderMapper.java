package com.testing.api.mapping;

import com.testing.api.integration.Order.OrderDto;
import com.testing.api.resource.OrderApi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "OrderApiOrderMapper")
public interface OrderApiOrderMapper {
    @Mapping(target = "products", ignore = true)
    OrderApi OrderDtoToOrderApi(OrderDto source);
    @Mapping(target = "products", ignore = true)
    OrderDto orderApiToOrderDto(OrderApi source);
}

