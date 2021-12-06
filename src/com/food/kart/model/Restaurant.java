package com.food.kart.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Restaurant {

    private final String id;

    private final String name;

    private final List<String> serviceablePincodes;

    private final List<Dish> dishes;

    private final Map<String, Integer> dishesToQuantity;

    private Rating rating;


    public void updateQuantiy(String dishName, Integer quantity) {
        this.dishesToQuantity.put(dishName, quantity);
    }
}
