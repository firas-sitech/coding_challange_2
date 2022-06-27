package me.sitech.exercise.two.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


/**
 * CSV model class used to map CSV file content the CSV Order are
 * ID, deliveryArea , productName, quantity, brand
 *
 * @Auther Firas Abbasi
 * @Version 1.0
 * */
@Data
@JsonPropertyOrder({"id", "deliveryArea", "productName", "quantity", "brand"})
public class Item {

    /**
     * CSV Id index number 1
     * */
    private String id;

    /**
     * CSV deliveryArea index number 2
     * */
    private String deliveryArea;

    /**
     * CSV productName index number 3
     * */
    private String productName;

    /**
     * CSV quantity index number 4
     * */
    private Double quantity;

    /**
     * CSV brand  index number 5
     * */
    private String brand;


    /**
     * toString method
     * */
    @Override
    public String toString() {
        return "{" +
        "id=" + getId() +
        ", deliveryArea='" + getDeliveryArea() + '\'' +
        ", productName=" + getProductName() +
        ", quantity=" + getQuantity() +
        ", brand=" + getBrand() +
        '}';
    }
}
