package com.gateway.api.mapping;

import com.gateway.api.integration.Order.OrderDto;
import com.gateway.api.resource.OrderApi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "OrderApiOrderMapper")
public interface OrderApiOrderMapper {
    @Mapping(target = "products", ignore = true)
    OrderApi OrderDtoToOrderApi(OrderDto source);
    @Mapping(target = "products", ignore = true)
    OrderDto orderApiToOrderDto(OrderApi source);
}

