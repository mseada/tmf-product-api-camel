package com.mseada.tmf.processor;

import com.mseada.tmf.model.Catalog;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory stub implementation of the Product Catalog service (TMF620).
 *
 * <p>Replace this with a JPA repository, external API call, or message-driven
 * backend once the persistence layer is defined.
 */
@Service("catalogService")
public class CatalogService {

    private final Map<String, Catalog> store = new ConcurrentHashMap<>();

    public CatalogService() {
        // Seed with one sample catalog
        Catalog seed = Catalog.builder()
            .id("CAT-001")
            .href("/productCatalogManagement/v4/catalog/CAT-001")
            .name("Default Product Catalog")
            .description("Main product catalog containing all active offerings")
            .catalogType("ProductCatalog")
            .lifecycleStatus("Active")
            .version("1.0")
            .validFor(OffsetDateTime.now().minusYears(1))
            .build();
        store.put(seed.getId(), seed);
    }

    public Catalog findById(String id) {
        return store.get(id);
    }

    public Catalog create(Catalog catalog) {
        if (catalog.getId() == null || catalog.getId().isBlank()) {
            catalog.setId("CAT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }
        catalog.setHref("/productCatalogManagement/v4/catalog/" + catalog.getId());
        store.put(catalog.getId(), catalog);
        return catalog;
    }
}
