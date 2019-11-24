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

    WebClient client = WebClient.create("http://localhost:8081");

    public ProductApi getProduct(Long id){
        return new RestTemplate().getForObject("http://localhost:8081/product/" + id, ProductApi.class);
    }

    public List<ProductApi> getProducts(){
        return new RestTemplate().exchange("http://localhost:8081/products",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductApi>>() {
                        }).getBody();
    }

    public ProductApi addProduct(ProductApi newProduct){
        return new RestTemplate().postForObject("http://localhost:8081/products"
                , new HttpEntity<>(newProduct), ProductApi.class);
    }

    public ProductApi buyProduct(long id) {
        return new RestTemplate().getForObject("http://localhost:8081/buyProduct/" + id, ProductApi.class);
    }
}
