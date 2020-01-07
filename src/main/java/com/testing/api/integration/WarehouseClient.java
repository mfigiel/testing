package com.testing.api.integration;

import com.testing.api.resource.ProductApi;
import com.testing.repository.entity.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class WarehouseClient {

    public static final String WAREHOUSE_ADRESS = "http://localhost:8081";
    WebClient client = WebClient.create(WAREHOUSE_ADRESS);

    public ProductApi getProduct(Long id){
        return new RestTemplate().getForObject(WAREHOUSE_ADRESS + "/product/" + id, ProductApi.class);
    }

    public List<ProductApi> getProducts(){
        return new RestTemplate().exchange(WAREHOUSE_ADRESS + "/products",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductApi>>() {
                        }).getBody();
    }

    public ProductApi addProduct(ProductApi newProduct){
        return new RestTemplate().postForObject(WAREHOUSE_ADRESS + "/products"
                , new HttpEntity<>(newProduct), ProductApi.class);
    }

    public ProductApi buyProduct(long id) {
        return new RestTemplate().getForObject(WAREHOUSE_ADRESS + "/buyProduct/" + id, ProductApi.class);
    }
}
