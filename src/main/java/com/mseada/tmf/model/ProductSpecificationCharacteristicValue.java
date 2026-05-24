package com.mseada.tmf.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TMF620 – A possible value for a ProductSpecificationCharacteristic.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSpecificationCharacteristicValue {
    private String valueType;
    private String value;
    private String unitOfMeasure;
    private Boolean isDefault;
}
