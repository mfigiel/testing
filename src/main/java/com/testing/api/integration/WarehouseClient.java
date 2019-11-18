package com.testing.api.integration;

import com.testing.repository.entity.Product;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class WarehouseClient {

    WebClient client = WebClient.create("http://localhost:8081");

    public Product getProduct(Long id){
        Mono<Product> producMono = client.get()
                .uri("/product/{id}", id)
                .retrieve()
                .bodyToMono(Product.class);

        return producMono.block();
    }

    public List<Product> getProducts(){
        Flux<Product> producMono = client.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class);

        return (List<Product>) producMono;
    }

}
