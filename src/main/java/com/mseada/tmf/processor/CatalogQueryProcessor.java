package com.mseada.tmf.processor;

import com.mseada.tmf.model.Catalog;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Processor that returns a stub list of {@link Catalog} resources.
 *
 * <p>Replace the hard-coded data here with a real repository or downstream
 * service call (e.g. via {@code camel-http} or {@code camel-jpa}).
 */
public class CatalogQueryProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        // Stub: return a single sample catalog
        Catalog sample = Catalog.builder()
            .id("CAT-001")
            .href("/productCatalogManagement/v4/catalog/CAT-001")
            .name("Default Product Catalog")
            .description("Main product catalog containing all active offerings")
            .catalogType("ProductCatalog")
            .lifecycleStatus("Active")
            .version("1.0")
            .validFor(OffsetDateTime.now().minusYears(1))
            .build();

        exchange.getMessage().setBody(List.of(sample));
        exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
        exchange.getMessage().setHeader("X-Total-Count", 1);
    }
}
