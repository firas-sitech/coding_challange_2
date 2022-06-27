package me.sitech.exercise.two.domain;

import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    /**
     * Display total product quantity
     * */
    private Double quantity = 0.0;

    /**
     * Map used to save the brand name with total ordered
     * */
    private Map<String,Integer> brandMap = new HashMap<>();
}
