package com.mseada.tmf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TMF620 – Monetary amount with currency code.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Money {
    /** Monetary amount (e.g. 575.00). */
    private java.math.BigDecimal value;
    /** ISO 4217 currency code (e.g. "EGP"). */
    private String unit;
}
