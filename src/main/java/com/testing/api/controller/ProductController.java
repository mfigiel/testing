package com.testing.api.controller;

import com.testing.api.resource.ProductApi;
import com.testing.api.resource.Transaction;
import com.testing.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping("/products")
    public List<ProductApi> getProducts() {
        return warehouseService.getProducts();
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody ProductApi product) {
        warehouseService.addProduct(product);
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ProductApi getProductInformation(@PathVariable("id") long id) {
        return warehouseService.getProduct(id);
    }

    @RequestMapping(value = "/buyProduct", method = RequestMethod.POST)
    public ProductApi buyProduct(@Valid @RequestBody Transaction transaction) {
        warehouseService.finishShopTransaction(transaction);
        return new ProductApi();
    }

}
