package com.testing.api.controller;

import com.testing.api.integration.WarehouseClient;
import com.testing.api.resource.ProductApi;
import com.testing.metrics.Metrics;
import com.testing.service.metrics.CounterService;
import io.micrometer.core.instrument.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    CounterService counterService;

    WarehouseClient warehouseClient = new WarehouseClient();

    @GetMapping("/products")
    public List<ProductApi> getProducts() {
        counterService.increment(Metrics.GET_PRODUCTS);
        return warehouseClient.getProducts();
    }

    @PostMapping("/products")
    void addProduct(@RequestBody ProductApi product) {
        warehouseClient.addProduct(product);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ProductApi getProductInformation(@PathVariable("id") long id) {
        counterService.increment(Metrics.PRODUCT_SOLD,
                Tags.of(Metrics.Tags.ID, String.valueOf(id)));
        return warehouseClient.getProduct(id);
    }

    @RequestMapping(value = "/buyProduct/{id}", method = RequestMethod.GET)
    public ProductApi buyProduct(@PathVariable("id") long id) {
        return warehouseClient.buyProduct(id);
    }

}
