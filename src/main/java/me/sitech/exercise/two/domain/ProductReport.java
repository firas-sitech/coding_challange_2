package me.sitech.exercise.two.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"productName", "orderPercent"})
public class ProductReport {

    /**
     * Report Product Name
     * */
    @JsonProperty("productName")
    private String productName;

    /**
     * Report Order Percent
     * */
    @JsonProperty("orderPercent")
    private Double orderPercent;
}
