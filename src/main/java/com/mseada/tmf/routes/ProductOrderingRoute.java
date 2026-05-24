package com.mseada.tmf.routes;

import com.mseada.tmf.model.ProductOrder;
import com.mseada.tmf.processor.ProductOrderProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

/**
 * TMF622 – Product Ordering Management API routes.
 *
 * <p>Exposes REST endpoints under
 * {@code /productOrderingManagement/v4/productOrder} conforming to the TMF622 spec.
 *
 * <p>Current stubs:
 * <ul>
 *   <li>GET  /productOrder          – list all product orders</li>
 *   <li>GET  /productOrder/{id}     – retrieve a product order by ID</li>
 *   <li>POST /productOrder          – create (submit) a new product order</li>
 *   <li>PATCH /productOrder/{id}    – partially update a product order</li>
 * </ul>
 */
@Component
public class ProductOrderingRoute extends RouteBuilder {

    private static final String BASE_PATH = "/productOrderingManagement/v4";

    @Override
    public void configure() {

        // ── Error handling ──────────────────────────────────────────────────
        onException(IllegalArgumentException.class)
            .handled(true)
            .setHeader("CamelHttpResponseCode", constant(400))
            .setBody(simple("{\"code\":\"400\",\"reason\":\"Bad Request\","
                + "\"message\":\"${exception.message}\"}"));

        onException(Exception.class)
            .handled(true)
            .setHeader("CamelHttpResponseCode", constant(500))
            .setBody(simple("{\"code\":\"500\",\"reason\":\"Internal Server Error\","
                + "\"message\":\"${exception.message}\"}"))
            .log("Unhandled error in ProductOrderingRoute: ${exception.message}");

        // ── REST DSL ────────────────────────────────────────────────────────
        rest(BASE_PATH + "/productOrder")
            .description("TMF622 Product Ordering Management")

            // LIST
            .get()
                .description("List all product orders")
                .param().name("state").type(RestParamType.query)
                    .description("Filter by order state").required(false).endParam()
                .param().name("offset").type(RestParamType.query)
                    .required(false).dataType("integer").endParam()
                .param().name("limit").type(RestParamType.query)
                    .required(false).dataType("integer").endParam()
                .outType(ProductOrder[].class)
                .to("direct:tmf622-listOrders")

            // GET by ID
            .get("/{id}")
                .description("Retrieve a product order by ID")
                .param().name("id").type(RestParamType.path)
                    .description("Identifier of the ProductOrder").required(true).endParam()
                .outType(ProductOrder.class)
                .to("direct:tmf622-getOrder")

            // CREATE
            .post()
                .description("Create (submit) a new product order")
                .type(ProductOrder.class)
                .outType(ProductOrder.class)
                .to("direct:tmf622-createOrder")

            // PATCH
            .patch("/{id}")
                .description("Partially update a product order")
                .param().name("id").type(RestParamType.path)
                    .required(true).endParam()
                .type(ProductOrder.class)
                .outType(ProductOrder.class)
                .to("direct:tmf622-patchOrder");

        // ── Internal routes ─────────────────────────────────────────────────

        from("direct:tmf622-listOrders")
            .routeId("tmf622-listOrders")
            .log("TMF622 LIST orders – state=${header.state}")
            .bean("productOrderService", "findAll")
            .marshal().json();

        from("direct:tmf622-getOrder")
            .routeId("tmf622-getOrder")
            .log("TMF622 GET order id=${header.id}")
            .bean("productOrderService", "findById(${header.id})")
            .choice()
                .when(body().isNull())
                    .setHeader("CamelHttpResponseCode", constant(404))
                    .setBody(simple("{\"code\":\"404\",\"reason\":\"Not Found\","
                        + "\"message\":\"ProductOrder ${header.id} not found\"}"))
                .otherwise()
                    .marshal().json()
            .end();

        from("direct:tmf622-createOrder")
            .routeId("tmf622-createOrder")
            .log("TMF622 CREATE order")
            .process(new ProductOrderProcessor())
            .bean("productOrderService", "create")
            .setHeader("CamelHttpResponseCode", constant(201))
            .marshal().json();

        from("direct:tmf622-patchOrder")
            .routeId("tmf622-patchOrder")
            .log("TMF622 PATCH order id=${header.id}")
            .bean("productOrderService", "patch(${header.id}, ${body})")
            .marshal().json();
    }
}
