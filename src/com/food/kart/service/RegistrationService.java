package com.food.kart.service;

import com.food.kart.exception.InvalidRequestException;
import com.food.kart.model.Dish;
import com.food.kart.model.Rating;
import com.food.kart.model.Restaurant;
import com.food.kart.model.User;
import com.food.kart.storage.RestaurantStorage;
import com.food.kart.storage.UserStorage;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RegistrationService {

    public void registerUser(String userName, String phoneNumber, String pincode) {

        if(UserStorage.phoneNumbers.contains(phoneNumber)) {
            throw new InvalidRequestException("Phone number is not unique");
        }

        String userId = UUID.randomUUID().toString();
        User user = new User(userId, userName, phoneNumber, pincode);

        UserStorage.addUser(userId, user);
        System.out.println("User has been registered!");
    }


    public void registerRestaurant(String restaurantName, List<String> pincodes, Map<String, Integer> dishesToQuantity, List<Dish> dishes) {
        String restaurantId = UUID.randomUUID().toString();

        Rating rating =  new Rating(0.0, 0);

        Restaurant restaurant = new Restaurant(restaurantId, restaurantName, pincodes, dishes, dishesToQuantity);
        restaurant.setRating(rating);

        RestaurantStorage.registerRestaurant(restaurantId, restaurant);
        System.out.println("Restaurant has been registered!");
    }
}
