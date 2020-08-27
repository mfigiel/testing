package com.gateway.containertests.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gateway.GatewayApplication;
import com.gateway.containertests.configuration.DockerEnvironmentConfiguration;
import com.gateway.containertests.util.JsonUtils;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Hooks;

import javax.annotation.PostConstruct;

import java.time.Duration;
import java.util.Properties;
import java.util.function.Consumer;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {GatewayApplication.class})
@ActiveProfiles("local-dev")
public abstract class AbstractIntegrationContainerTest extends DockerEnvironmentConfiguration {

    @LocalServerPort
    protected int APPLICATION_PORT;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected RestTemplate testRestTemplate;

    protected WebTestClient webTestClient;
    protected JsonUtils jsonUtils;

    @Autowired
    private ConfigurableEnvironment environment;
    @Value("${warehouse_address}")
    private static final String WAREHOUSE_ADRESS = "http://warehouse";

    @BeforeAll
    static void abstractBeforeAll() {
        boolean useLocalEnvironment = isLocal();
        boolean useExistingEnv = useExistingEnv();
        boolean runWithoutContainers = DockerEnvironmentConfiguration.runWithoutContainers();

        if (useLocalEnvironment && !useExistingEnv && !runWithoutContainers) {
            localDockerEnvironment.start();
        }
        initializeApplicationContext(useLocalEnvironment, runWithoutContainers);
    }

    @PostConstruct
    private void setUp() {
        jsonUtils = new JsonUtils(objectMapper);

        webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + APPLICATION_PORT)
                .responseTimeout(Duration.ofSeconds(30)).build();
        Hooks.onOperatorDebug();
    }

    private static void initializeApplicationContext(boolean useLocalConfiguration, boolean runWithoutContainers) {

        if (!useLocalConfiguration) {
            DockerEnvironmentConfiguration.remote();
        } else {
            DockerEnvironmentConfiguration.local();
        }

        ImmutableMap.Builder<String, String> propertiesBuilder = new ImmutableMap.Builder<String, String>()
                .put("eureka.client.enabled", "false")
                .put("PORT", "0")
                .put("warehouse_address", "localhost:" + WAREHOUSE_PORT);

        propertiesBuilder
                .build().forEach(System::setProperty);
    }

    protected Consumer<String> contains(String subString) {
        return s -> {
            if (!s.contains(subString)) {
                throw new AssertionError("Should contain substring" + subString);
            }
        };
    }
}
