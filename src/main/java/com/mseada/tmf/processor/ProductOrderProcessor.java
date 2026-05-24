package com.mseada.tmf.processor;

import com.mseada.tmf.model.ProductOrder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Enriches an incoming {@link ProductOrder} before it is persisted.
 *
 * <p>Responsible for:
 * <ul>
 *   <li>Assigning a unique ID if one is not already present</li>
 *   <li>Setting the initial state to {@code acknowledged}</li>
 *   <li>Stamping the order date</li>
 * </ul>
 */
public class ProductOrderProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        ProductOrder order = exchange.getMessage().getBody(ProductOrder.class);

        if (order == null) {
            throw new IllegalArgumentException("Request body must be a valid ProductOrder");
        }

        // Assign system-generated fields
        if (order.getId() == null || order.getId().isBlank()) {
            order.setId("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }

        order.setHref("/productOrderingManagement/v4/productOrder/" + order.getId());
        order.setState("acknowledged");
        order.setOrderDate(OffsetDateTime.now());

        exchange.getMessage().setBody(order);
    }
}
