package com.mseada.tmf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TMF620 – Price associated with a ProductOffering.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOfferingPrice {
    private String id;
    private String href;
    private String name;
    private String description;
    /** e.g. "recurring", "nonRecurring", "usage" */
    private String priceType;
    /** How often the charge recurs, e.g. "monthly". */
    private String recurringChargePeriodType;
    private Money price;
    private TimePeriod validFor;
    private String lifecycleStatus;
}
