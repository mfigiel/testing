package com.gateway.containertests.tests;

import com.gateway.api.integration.ClientServiceClient;
import com.gateway.api.integration.Order.OrderServiceClient;
import com.gateway.api.integration.Warehouse.BuyProductsRequest;
import com.gateway.api.integration.Warehouse.WarehouseClient;
import com.gateway.api.resource.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class GatewayContainerTest extends AbstractIntegrationContainerTest {

    @Autowired
    WarehouseClient warehouseClient;
    @Autowired
    ClientServiceClient serviceClient;
    @Autowired
    OrderServiceClient orderServiceClient;

    @Test
    void testValidations() {
        warehouseClient.setWarehouseAddress("http://localhost:" + WAREHOUSE_PORT);

        //for
        webTestClient.get()
                .uri("/products")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("[]");
    }

    @Test
    void testValidations2() {
        warehouseClient.setWarehouseAddress("http://localhost:" + WAREHOUSE_PORT);

        //for
        webTestClient.get()
                .uri("/product/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("");
    }

    @Test
    void testValidations3() {
        warehouseClient.setWarehouseAddress("http://localhost:" + WAREHOUSE_PORT);

        List id = new ArrayList<Long>();
        id.add(1L);
        webTestClient.post()
                .uri("/buyProduct")
                .syncBody(new BuyProductsRequest(id))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .json("");
    }

    @Test
    void testValidations4() {
        warehouseClient.setWarehouseAddress("http://localhost:" + WAREHOUSE_PORT);
        serviceClient.setClientServiceAddress("http://localhost:" + CLIENTS_PORT);
        orderServiceClient.setOrderServiceAddress("http://localhost:" + ORDERS_PORT);

        ProductApi product = new ProductApi();
        product.setCategory("category");
        product.setDescription("description");
        product.setName("name");
        product.setUnitPrice(new BigDecimal(10));
        product.setUnitsInStock(10L);
        product.setUnitsInOrder(0L);

        //for
        webTestClient.get()
                .uri("/products")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("[]");

        webTestClient.post()
                .uri("/products")
                .syncBody(product)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("");

        //for
        webTestClient.get()
                .uri("/products")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("[{\"id\":1,\"name\":\"name\",\"unitPrice\":10.00,\"description\":\"description\",\"category\":\"category\",\"unitsInStock\":10,\"unitsInOrder\":0,\"state\":\"BOUGHT\"}]");

        Transaction transaction = new Transaction();
        OrderApi orderApi = new OrderApi();
        ProductApi productApi = new ProductApi();
        productApi.setId(1L);
        List<ProductApi> productApiList = new ArrayList<>();
        productApiList.add(productApi);
        orderApi.setProducts(productApiList);
        transaction.setOrder(orderApi);
        ClientApi clientApi = new ClientApi();
        clientApi.setName("testoweImie");
        clientApi.setSurname("testoweNazwisko");
        AddressApi addressApi = new AddressApi();
        addressApi.setStreet("testowaUlica");
        addressApi.setZipCode("44-100");
        addressApi.setCity("testoweMIasto");
        addressApi.setHouseNumber(5);
        clientApi.setAddress(addressApi);
        transaction.setClient(clientApi);


        webTestClient.post()
                .uri("/buyProduct")
                .syncBody(transaction)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Transaction.class)
                .getResponseBody()
                .doOnNext(transactionResult -> {
                    assertThat(verifyTransaction(transactionResult)).isTrue();
                });




        webTestClient.get()
                .uri("/products")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("[{\"id\":1,\"name\":\"name\",\"unitPrice\":10.00,\"description\":\"description\",\"category\":\"category\",\"unitsInStock\":9,\"unitsInOrder\":1,\"state\":\"BOUGHT\"}]");
    }

    private boolean verifyTransaction(Transaction transaction) {
        final OrderApi order = transaction.getOrder();
        Assertions.assertAll(() -> Optional
                .ofNullable(order.getProducts().stream().findFirst())
                .orElseThrow(RuntimeException::new));

        final ProductApi productApi = order.getProducts().stream().findFirst().get();
        if (order.getId()== 1 && transaction.getClient().getId() == 1
                && productApi.getUnitsInOrder() == 1
                && productApi.getUnitsInStock() == 9
                && productApi.getState().equals(ProductState.BOUGHT)) {
            return true;
        }

        return false;
    }

}
