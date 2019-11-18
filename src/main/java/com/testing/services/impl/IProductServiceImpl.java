package com.testing.services.impl;


import com.packt.webstore.domain.Products.Product;
import com.testing.services.IProductService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class IProductServiceImpl implements IProductService {

    public List<Product> getAllProducts() {
        RestTemplate restTemplate = new RestTemplate();
         //restTemplate.getForObject("http://localhost:8081/allProducts", List.class);

        /*MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
                new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(
                Arrays.asList(
                        MediaType.APPLICATION_JSON,
                        MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);*/

        ResponseEntity<List<String>> response = restTemplate.exchange(
                "http://localhost:8081/allProducts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>(){});
        List<String> employees = response.getBody();

        //ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity("http://localhost:8081/allProducts", Product[].class);
        //Product[] objects = responseEntity.getBody();

        return new ArrayList<Product>();
    }

    public Product getProductById(String var1) {
        return null;
    }

    public void addProduct(Product var1) {

    }

    public void withdrawProduct(int var1) {

    }

    public void editProduct(Product var1) {

    }

    public List<Product> getProductsByCategory(String var1) {
        return null;
    }

    public List<Product> getProductsByParameter(String var1, String var2) {
        return null;
    }
}
