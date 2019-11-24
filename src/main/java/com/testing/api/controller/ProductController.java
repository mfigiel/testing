package com.testing.api.controller;

import com.testing.api.integration.WarehouseClient;
import com.testing.api.mapping.ProductApiProductMapper;
import com.testing.api.mapping.ProductApiProductMapperImpl;
import com.testing.api.resource.ProductApi;
import com.testing.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    WarehouseClient warehouseClient = new WarehouseClient();

    @GetMapping("/products")
    public List<ProductApi> getProducts() {
        List<ProductApi> a = warehouseClient.getProducts();
        return warehouseClient.getProducts();
    }

    @PostMapping("/products")
    void addProduct(@RequestBody ProductApi product) {
        warehouseClient.addProduct(product);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ProductApi getProductInformation(@PathVariable("id") long id) {
        ProductApi a = warehouseClient.getProduct(id);
        return a;
    }

    @RequestMapping(value = "/buyProduct/{id}", method = RequestMethod.GET)
    public ProductApi buyProduct(@PathVariable("id") long id) {
        ProductApi a = warehouseClient.buyProduct(id);
        return a;
    }

}
