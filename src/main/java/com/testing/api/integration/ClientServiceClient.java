package com.testing.api.integration;

import com.testing.api.resource.ProductApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class ClientServiceClient {

    public static final String WAREHOUSE_CLIENT_ADDRESS = "http://localhost:8082";
    WebClient client = WebClient.create(WAREHOUSE_CLIENT_ADDRESS);

    public ProductApi getProduct(Long id){
        return new RestTemplate().getForObject(WAREHOUSE_CLIENT_ADDRESS + "/client/" + id, ProductApi.class);
    }

    public List<ProductApi> getProducts(){
        return new RestTemplate().exchange(WAREHOUSE_CLIENT_ADDRESS + "/clients",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductApi>>() {
                        }).getBody();
    }

    public ProductApi addProduct(ProductApi newProduct){
        return new RestTemplate().postForObject(WAREHOUSE_CLIENT_ADDRESS + "/clients"
                , new HttpEntity<>(newProduct), ProductApi.class);
    }

}
