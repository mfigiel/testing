package com.testing.api.integration;

import com.testing.api.resource.ProductApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class WarehouseClient {

    public static final String WAREHOUSE_ADRESS = "http://localhost:8081";
    public static final String ALL_PRODUCTS_ENDPOINT = "/products";
    public static final String SINGLE_PRODUCT_ENDPOINT = "/product/";
    public static final String BUY_PRODUCT_ENDPOINT = "/buyProduct/";

    public ProductApi getProduct(Long id){
        return new RestTemplate().getForObject(WAREHOUSE_ADRESS + SINGLE_PRODUCT_ENDPOINT + id, ProductApi.class);
    }

    public List<ProductApi> getProducts(){
        return new RestTemplate().exchange(WAREHOUSE_ADRESS + ALL_PRODUCTS_ENDPOINT,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductApi>>() {
                        }).getBody();
    }

    public ProductApi addProduct(ProductApi newProduct){
        return new RestTemplate().postForObject(WAREHOUSE_ADRESS + ALL_PRODUCTS_ENDPOINT
                , new HttpEntity<>(newProduct), ProductApi.class);
    }

    public ProductApi buyProduct(long id) {
        return new RestTemplate().getForObject(WAREHOUSE_ADRESS + BUY_PRODUCT_ENDPOINT + id, ProductApi.class);
    }
}
