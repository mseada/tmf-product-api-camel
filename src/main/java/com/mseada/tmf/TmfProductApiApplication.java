package com.mseada.tmf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the TM Forum Product API service.
 *
 * <p>Implements:
 * <ul>
 *   <li>TMF620 – Product Catalog Management API</li>
 *   <li>TMF622 – Product Ordering Management API</li>
 * </ul>
 *
 * <p>Routes are built with Apache Camel and exposed via the Camel Servlet
 * component mounted on Spring Boot's embedded web server.
 */
@SpringBootApplication
public class TmfProductApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmfProductApiApplication.class, args);
    }
}
