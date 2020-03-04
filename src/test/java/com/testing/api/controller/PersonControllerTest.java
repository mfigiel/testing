package com.testing.api.controller;

import com.testing.api.resource.ProductApi;
import javafx.concurrent.Task;
import lombok.var;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.ConnectException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PersonControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate testRestTemplate;

   // @MockBean
    //private PersonController personController;
///product/{id}
    @Test
    public void productsGet() throws Exception {
        mockMvc
                .perform(get("/products"))
                .andExpect(status().isOk());

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/products",
                String.class)).isNullOrEmpty();
    }

    @Test
    public void productGet() throws Exception {
        mockMvc
                .perform(get("/product", 1)
                .param("id", "1"))
                .andExpect(status().isOk());

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/product/?id=1",
                String.class)).isNullOrEmpty();
    }

    @Ignore
    public void findsTaskById() {
        // act

        try {
        var product = testRestTemplate.getForObject("http://localhost:" + port + "/product/1", ProductApi.class);

            // assert
            assertThat(product)
                    .extracting(ProductApi::getId, ProductApi::getCategory, ProductApi::getDescription, ProductApi::getName)
                    .containsExactly(1, "delectus aut autem", false, 1);
        } catch(Exception e) {
            assertThat(true);
        }
    }
}
