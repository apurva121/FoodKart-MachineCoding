package com.food.kart.model;

import lombok.Value;

@Value
public class Order {

    private final String orderId;

    private final String userId;

    private final String restaurantId;

    private final Double orderTotal;
}
