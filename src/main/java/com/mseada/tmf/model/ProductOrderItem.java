package com.mseada.tmf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * TMF622 – A single line item inside a {@link ProductOrder}.
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOrderItem {

    /** Line-item identifier (unique within the order). */
    private String id;

    /** Quantity ordered. */
    private Integer quantity;

    /**
     * Action to perform on the product.
     *
     * <p>Allowed values: add, modify, delete, noChange.
     */
    private String action;

    /** State of this individual item. */
    private String state;

    /** Reference to the product offering being ordered. */
    private ProductOfferingRef productOffering;
}
