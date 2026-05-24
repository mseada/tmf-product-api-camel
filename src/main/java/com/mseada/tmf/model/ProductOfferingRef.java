package com.mseada.tmf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * Lightweight reference to a ProductOffering (TMF620).
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOfferingRef {

    private String id;
    private String href;
    private String name;

    /** Polymorphism hint – always "ProductOffering". */
    private String atReferredType;
}
