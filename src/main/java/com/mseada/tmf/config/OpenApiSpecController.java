package com.mseada.tmf.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Serves the official TM Forum OpenAPI reference specification YAML files.
 *
 * <p>Two endpoints are exposed:
 * <ul>
 *   <li>{@code GET /api-docs/tmf620} – TMF620 Product Catalog Management (OAS 3.0, v5)</li>
 *   <li>{@code GET /api-docs/tmf622} – TMF622 Product Ordering Management (Swagger 2.0, v4)</li>
 * </ul>
 *
 * <p>These endpoints are registered in {@code springdoc.swagger-ui.urls} so that
 * the Swagger UI at {@code /swagger-ui.html} lists both specs alongside the
 * live Camel-generated spec.
 */
@RestController
@RequestMapping("/api-docs")
public class OpenApiSpecController {

    private static final MediaType MEDIA_TYPE_YAML =
            MediaType.parseMediaType("application/yaml;charset=UTF-8");

    /**
     * Returns the TMF620 Product Catalog Management official spec (OAS 3.0, v5.0.0).
     */
    @GetMapping(value = "/tmf620", produces = "application/yaml")
    public ResponseEntity<Resource> tmf620Spec() {
        Resource resource = new ClassPathResource(
                "openapi/tmf620-product-catalog-management.yaml");
        return ResponseEntity.ok()
                .contentType(MEDIA_TYPE_YAML)
                .body(resource);
    }

    /**
     * Returns the TMF622 Product Ordering Management official spec (Swagger 2.0, v4.0.0).
     */
    @GetMapping(value = "/tmf622", produces = "application/yaml")
    public ResponseEntity<Resource> tmf622Spec() {
        Resource resource = new ClassPathResource(
                "openapi/tmf622-product-ordering-management.yaml");
        return ResponseEntity.ok()
                .contentType(MEDIA_TYPE_YAML)
                .body(resource);
    }
}
