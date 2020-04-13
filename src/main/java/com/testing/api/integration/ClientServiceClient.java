package com.testing.api.integration;

import com.testing.api.resource.ClientApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ClientServiceClient {

    private static final String WAREHOUSE_CLIENT_ADDRESS = "http://localhost:8083";

    public ClientApi getClient(Long id){
            return new RestTemplate().getForObject(WAREHOUSE_CLIENT_ADDRESS + "/client/" + id, ClientApi.class);
    }

    public List<ClientApi> getClients(){
        return new RestTemplate().exchange(WAREHOUSE_CLIENT_ADDRESS + "/clients",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<ClientApi>>() {
                        }).getBody();
    }

    public ClientApi addClient(ClientApi newClient){
        return new RestTemplate().postForObject(WAREHOUSE_CLIENT_ADDRESS + "/clients"
                , new HttpEntity<>(newClient), ClientApi.class);
    }

}
