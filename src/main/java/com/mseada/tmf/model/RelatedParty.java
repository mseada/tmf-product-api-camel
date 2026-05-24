package com.mseada.tmf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * Common TMF reference to a party playing a role (e.g. owner, seller).
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelatedParty {

    private String id;
    private String href;
    private String name;
    private String role;

    /** Polymorphism hint – e.g. "Individual", "Organization". */
    private String atReferredType;
}
