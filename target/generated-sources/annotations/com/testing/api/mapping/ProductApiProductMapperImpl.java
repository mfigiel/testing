package com.testing.api.mapping;

import com.testing.api.resource.ProductApi;
import com.testing.repository.entity.Product;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-01T19:36:53+0100",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class ProductApiProductMapperImpl implements ProductApiProductMapper {

    @Override
    public ProductApi productDtoToProductApi(Optional<Product> source) {
        if ( source == null ) {
            return null;
        }

        ProductApi productApi = new ProductApi();

        return productApi;
    }

    @Override
    public Product productApiToProductDto(ProductApi source) {
        if ( source == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( source.getId() );
        product.setName( source.getName() );
        product.setUnitPrice( source.getUnitPrice() );
        product.setDescription( source.getDescription() );
        product.setCategory( source.getCategory() );
        product.setUnitsInStock( source.getUnitsInStock() );
        product.setUnitsInOrder( source.getUnitsInOrder() );

        return product;
    }
}
