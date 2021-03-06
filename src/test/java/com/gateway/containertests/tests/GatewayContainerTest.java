package com.gateway.containertests.tests;

import com.gateway.api.integration.Warehouse.BuyProductsRequest;
import com.gateway.api.resource.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GatewayContainerTest extends AbstractIntegrationContainerTest {

    @Order(1)
    @Test
    void getProductsFromEmptyDatabase() {
        getAllProductsFromEmptyDatabase();

        webTestClient.get()
                .uri("/product/1")
                .exchange()
                .expectStatus()
                .is5xxServerError();

        webTestClient.post()
                .uri("/buyProduct")
                .body(Mono.just(BuyProductsRequest.builder().productsId(new ArrayList<>(Arrays.asList(1L))).build()), BuyProductsRequest.class)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .json("");
    }

    @Test
    void addProductAndBuy() {
        getAllProductsFromEmptyDatabase();

        addProductToWarehouse(getSampleProduct());

        getAllProductsFromDatabase();

        buyProduct();

        getAllProductsFromDatabaseAfterBuyOperation();
    }

    private void getAllProductsFromDatabaseAfterBuyOperation() {
        webTestClient.get()
                .uri("/products")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .json("[{\"id\":1,\"name\":\"name\",\"unitPrice\":10.00,\"description\":\"description\",\"category\":\"category\",\"unitsInStock\":9,\"unitsInOrder\":1,\"state\":\"BOUGHT\"}]");
    }

    private void buyProduct() {
        webTestClient.post()
                .uri("/buyProduct")
                .body(Mono.just(createSampleTransaction()), Transaction.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Transaction.class)
                .getResponseBody()
                .doOnNext(transactionResult -> assertThat(verifyTransaction(transactionResult)).isTrue());
    }


    private boolean verifyTransaction(Transaction transaction) {
        final OrderApi order = transaction.getOrder();
        Assertions.assertAll(() -> Optional
                .ofNullable(order.getProducts().stream().findFirst())
                .orElseThrow(RuntimeException::new));

        final ProductApi productApi = order.getProducts().stream().findFirst().get();
        if (order.getId() == 1 && transaction.getClient().getId() == 1
                && productApi.getUnitsInOrder() == 1
                && productApi.getUnitsInStock() == 9
                && productApi.getState().equals(ProductState.BOUGHT)) {
            return true;
        }

        return false;
    }

    private void getAllProductsFromDatabase() {
        webTestClient.get()
                .uri("/products")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("[{\"id\":1,\"name\":\"name\",\"unitPrice\":10.00,\"description\":\"description\",\"category\":\"category\",\"unitsInStock\":10,\"unitsInOrder\":0,\"state\":\"BOUGHT\"}]");
    }

    private void addProductToWarehouse(ProductApi product) {
        webTestClient.post()
                .uri("/products")
                .body(Mono.just(product), ProductApi.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("");
    }

    private void getAllProductsFromEmptyDatabase() {
        webTestClient.get()
                .uri("/products")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("[]");
    }

    @NotNull
    private Transaction createSampleTransaction() {
        Transaction transaction = new Transaction();
        OrderApi orderApi = new OrderApi();
        List<ProductApi> productApiList = new ArrayList<>();

        productApiList.add(ProductApi.builder().id(1L).build());
        orderApi.setProducts(productApiList);
        transaction.setOrder(orderApi);
        transaction.setClient(getSampleClientApi());
        return transaction;
    }

    @NotNull
    private ClientApi getSampleClientApi() {
        ClientApi clientApi = new ClientApi();
        clientApi.setName("testoweImie");
        clientApi.setSurname("testoweNazwisko");
        clientApi.setAddress(getSampleAddressApi());
        return clientApi;
    }

    @NotNull
    private AddressApi getSampleAddressApi() {
        AddressApi addressApi = new AddressApi();
        addressApi.setStreet("testowaUlica");
        addressApi.setZipCode("44-100");
        addressApi.setCity("testoweMIasto");
        addressApi.setHouseNumber(5);
        return addressApi;
    }

    private ProductApi getSampleProduct() {
        return ProductApi.builder()
                .name("name")
                .category("category")
                .description("description")
                .unitPrice(new BigDecimal(10))
                .unitsInStock(10L)
                .unitsInOrder(0L)
                .build();
    }

}
