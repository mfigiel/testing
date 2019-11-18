package com.testing.api.controller;

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

    ProductApiProductMapperImpl productApiProductMapper = new ProductApiProductMapperImpl();
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<ProductApi> getProducts() {
        List products = (List) productRepository.findAll();
        return products;
    }

    @PostMapping("/products")
    void addProduct(@RequestBody ProductApi product) {

        productRepository.save(productApiProductMapper.productApiToProductDto(product));
    }

    @RequestMapping(value = "/productinformation/{id}", method = RequestMethod.GET)
    public ProductApi getProductInformation(@PathVariable("id") long id) {
        Optional<ProductApi> product = Optional.ofNullable(productApiProductMapper.productDtoToProductApi(productRepository.findById(id)));
        return product.get();
    }

}
