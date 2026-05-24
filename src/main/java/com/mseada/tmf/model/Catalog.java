package com.mseada.tmf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * TMF620 – Catalog resource.
 *
 * <p>Represents a collection of product-related resources (product offerings,
 * product specifications, etc.) that are made available to customers.
 *
 * @see <a href="https://www.tmforum.org/resources/standard/tmf620-product-catalog-management-api-rest-specification-r19-0-0/">
 *      TMF620 spec</a>
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Catalog {

    /** Unique identifier of the catalog. */
    private String id;

    /** Human-readable name of the catalog. */
    private String name;

    /** Free-form description. */
    private String description;

    /** Catalog type (e.g. "ProductCatalog"). */
    private String catalogType;

    /** Lifecycle status (Active, Launched, Obsolete, Retired). */
    private String lifecycleStatus;

    /** Date/time the catalog becomes valid. */
    private OffsetDateTime validFor;

    /** Date/time the catalog expires. */
    private OffsetDateTime validForEnd;

    /** Catalog version string. */
    private String version;

    /** URL of the catalog resource. */
    private String href;

    /** Top-level categories in this catalog. */
    private List<CategoryRef> category;

    /** Related parties (owner, seller, etc.). */
    private List<RelatedParty> relatedParty;
}
