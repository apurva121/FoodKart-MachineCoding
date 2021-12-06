package com.food.kart.model;

import lombok.Value;

@Value
public class User {

    private final String id;

    private final String name;

    private final String phoneNumber;

    private final String pinCode;

}
