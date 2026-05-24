package com.mseada.tmf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * TMF622 – ProductOrder resource.
 *
 * <p>Represents a request from a customer to purchase or modify one or more
 * product offerings.
 *
 * @see <a href="https://www.tmforum.org/resources/standard/tmf622-product-ordering-management-api-rest-specification-r19-0-0/">
 *      TMF622 spec</a>
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOrder {

    /** Unique identifier assigned when the order is created. */
    private String id;

    /** URL of the product order resource. */
    private String href;

    /**
     * Order state machine value.
     *
     * <p>Allowed values: acknowledged, pending, held, inProgress,
     * cancelled, completed, failed, partial.
     */
    private String state;

    /** Free-text note describing the order. */
    private String description;

    /** External reference (e.g. CRM order number). */
    private String externalId;

    /** Requested completion date supplied by the customer. */
    private OffsetDateTime requestedCompletionDate;

    /** Requested start date. */
    private OffsetDateTime requestedStartDate;

    /** Date the order was created. */
    private OffsetDateTime orderDate;

    /** Completion date (populated after the order finishes). */
    private OffsetDateTime completionDate;

    /** The order items (one per product offering). */
    private List<ProductOrderItem> productOrderItem;

    /** Related parties involved in this order. */
    private List<RelatedParty> relatedParty;
}
