package com.gateway.api.integration.Warehouse;

import com.gateway.api.resource.ProductApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class WarehouseClient {

    @Value("${warehouseAddress:http://warehouse}")
    private String warehouseAddress = "http://warehouse";

    private static final String ALL_PRODUCTS_ENDPOINT = "/products";
    private static final String SINGLE_PRODUCT_ENDPOINT = "/product/";
    private static final String BUY_PRODUCT_ENDPOINT = "/buyProduct";

    @Autowired
    private RestTemplate loadBalancedRestTemplate;

    public ProductApi getProduct(Long id){
        return loadBalancedRestTemplate.getForObject(warehouseAddress + SINGLE_PRODUCT_ENDPOINT + id, ProductApi.class);
    }

    public List<ProductApi> getProducts(){
        return loadBalancedRestTemplate.exchange(warehouseAddress + ALL_PRODUCTS_ENDPOINT,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductApi>>() {
                        }).getBody();
    }

    public ProductApi addProduct(ProductApi newProduct){
        return loadBalancedRestTemplate.postForObject(warehouseAddress + ALL_PRODUCTS_ENDPOINT
                , new HttpEntity<>(newProduct), ProductApi.class);
    }

    public BuyProductsResponse buyProduct(BuyProductsRequest buyProductsRequest) {
        return loadBalancedRestTemplate.postForObject(warehouseAddress + BUY_PRODUCT_ENDPOINT
                , new HttpEntity<>(buyProductsRequest), BuyProductsResponse.class);
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }
}
