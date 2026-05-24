package com.mseada.tmf;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Smoke test – verifies that the Spring context and CamelContext start cleanly.
 *
 * <p>Extend this class to add route-level tests using {@link ProducerTemplate}
 * and {@code @MockEndpoints}.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@CamelSpringBootTest
class ProductCatalogRouteTest {

    @Autowired
    private CamelContext camelContext;

    @Test
    void contextLoads() {
        assertThat(camelContext).isNotNull();
        assertThat(camelContext.isStarted()).isTrue();
    }

    @Test
    void tmf620RoutesAreRegistered() {
        assertThat(camelContext.getRoute("tmf620-listCatalogs")).isNotNull();
        assertThat(camelContext.getRoute("tmf620-getCatalog")).isNotNull();
        assertThat(camelContext.getRoute("tmf620-createCatalog")).isNotNull();
    }

    @Test
    void tmf622RoutesAreRegistered() {
        assertThat(camelContext.getRoute("tmf622-listOrders")).isNotNull();
        assertThat(camelContext.getRoute("tmf622-getOrder")).isNotNull();
        assertThat(camelContext.getRoute("tmf622-createOrder")).isNotNull();
        assertThat(camelContext.getRoute("tmf622-patchOrder")).isNotNull();
    }
}
