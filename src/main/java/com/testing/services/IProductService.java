package com.testing.services;


import com.packt.webstore.domain.Products.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    Product getProductById(String var1);

    void addProduct(Product var1);

    void withdrawProduct(int var1);

    void editProduct(Product var1);

    List<Product> getProductsByCategory(String var1);

    List<Product> getProductsByParameter(String var1, String var2);
}
