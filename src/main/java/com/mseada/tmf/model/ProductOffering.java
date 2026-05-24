package com.mseada.tmf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TMF620 – A ProductOffering represents a sellable package of products.
 * Each Vodafone Egypt rate plan maps to one ProductOffering.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOffering {
    private String id;
    private String href;
    private String name;
    private String description;
    private String version;
    private String lifecycleStatus;
    private TimePeriod validFor;
    private Boolean isSellable;
    /** e.g. "individual", "business" */
    private String category;
    private List<ProductOfferingPrice> productOfferingPrice;
    private ProductSpecification productSpecification;
    private List<String> channel;
}
