package com.mseada.tmf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TMF620 – ProductSpecification describes the technical characteristics
 * of a product (data, voice, SMS allowances, etc.).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSpecification {
    private String id;
    private String href;
    private String name;
    private String description;
    private String version;
    private String lifecycleStatus;
    private TimePeriod validFor;
    /** e.g. "MobileDataPlan", "PostpaidPlan" */
    private String productNumber;
    private List<ProductSpecificationCharacteristic> productSpecCharacteristic;
}
