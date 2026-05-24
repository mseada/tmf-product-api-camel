package com.mseada.tmf.routes;

import com.mseada.tmf.model.Catalog;
import com.mseada.tmf.processor.CatalogQueryProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

/**
 * TMF620 – Product Catalog Management API routes.
 *
 * <p>Exposes REST endpoints under
 * {@code /productCatalogManagement/v4/catalog} conforming to the TMF620 spec.
 *
 * <p>Current stubs:
 * <ul>
 *   <li>GET  /catalog          – list all catalogs (supports ?fields, ?offset, ?limit)</li>
 *   <li>GET  /catalog/{id}     – retrieve a specific catalog by ID</li>
 *   <li>POST /catalog          – create a new catalog</li>
 * </ul>
 *
 * <p>Each REST endpoint delegates to a {@code direct:} route so that the
 * business logic can be tested independently of HTTP concerns.
 */
@Component
public class ProductCatalogRoute extends RouteBuilder {

    private static final String BASE_PATH = "/productCatalogManagement/v4";

    @Override
    public void configure() {

        // ── Error handling ──────────────────────────────────────────────────
        onException(Exception.class)
            .handled(true)
            .setHeader("CamelHttpResponseCode", constant(500))
            .setBody(simple("{\"code\":\"500\",\"reason\":\"Internal Server Error\","
                + "\"message\":\"${exception.message}\"}"))
            .log("Unhandled error in ProductCatalogRoute: ${exception.message}");

        // ── REST DSL ────────────────────────────────────────────────────────
        rest(BASE_PATH + "/catalog")
            .description("TMF620 Product Catalog Management")

            // LIST
            .get()
                .description("List all product catalogs")
                .param().name("fields").type(RestParamType.query)
                    .description("Comma-separated list of field names to include")
                    .required(false).endParam()
                .param().name("offset").type(RestParamType.query)
                    .description("Requested index for start of resources to be provided")
                    .required(false).dataType("integer").endParam()
                .param().name("limit").type(RestParamType.query)
                    .description("Requested number of resources to be provided in response")
                    .required(false).dataType("integer").endParam()
                .outType(Catalog[].class)
                .to("direct:tmf620-listCatalogs")

            // GET by ID
            .get("/{id}")
                .description("Retrieve a product catalog by ID")
                .param().name("id").type(RestParamType.path)
                    .description("Identifier of the Catalog").required(true).endParam()
                .outType(Catalog.class)
                .to("direct:tmf620-getCatalog")

            // CREATE
            .post()
                .description("Create a new product catalog")
                .type(Catalog.class)
                .outType(Catalog.class)
                .to("direct:tmf620-createCatalog");

        // ── Internal routes ─────────────────────────────────────────────────

        from("direct:tmf620-listCatalogs")
            .routeId("tmf620-listCatalogs")
            .log("TMF620 LIST catalogs – fields=${header.fields} "
                + "offset=${header.offset} limit=${header.limit}")
            .process(new CatalogQueryProcessor())
            .marshal().json();

        from("direct:tmf620-getCatalog")
            .routeId("tmf620-getCatalog")
            .log("TMF620 GET catalog id=${header.id}")
            .choice()
                .when(header("id").isEqualTo("unknown"))
                    .setHeader("CamelHttpResponseCode", constant(404))
                    .setBody(simple("{\"code\":\"404\",\"reason\":\"Not Found\","
                        + "\"message\":\"Catalog ${header.id} not found\"}"))
                .otherwise()
                    .bean("catalogService", "findById(${header.id})")
                    .marshal().json()
            .end();

        from("direct:tmf620-createCatalog")
            .routeId("tmf620-createCatalog")
            .log("TMF620 CREATE catalog – body=${body}")
            .bean("catalogService", "create")
            .setHeader("CamelHttpResponseCode", constant(201))
            .marshal().json();
    }
}
