package com.mseada.tmf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * TMF620 – Lightweight reference to a product category.
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryRef {

    private String id;
    private String href;
    private String name;
    private String version;

    /** Polymorphism hint – always "CategoryRef". */
    private String atReferredType;
}
