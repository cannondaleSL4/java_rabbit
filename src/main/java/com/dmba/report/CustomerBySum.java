package com.dmba.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


/**
 * Represents a customer with their associated total sum.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerBySum {

    /**
     * The name of the customer.
     */
    @JsonProperty
    private String name;

    /**
     * The total sum associated with the customer.
     */
    @JsonProperty
    private BigDecimal sum;
}
