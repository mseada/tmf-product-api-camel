package com.mseada.tmf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TMF620 – A characteristic (feature) of a ProductSpecification,
 * e.g. "dataAllowance", "voiceMinutes", "smsAllowance".
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSpecificationCharacteristic {
    private String name;
    private String description;
    private String valueType;
    private List<ProductSpecificationCharacteristicValue> productSpecCharacteristicValue;
}
