package com.food.kart.service;

import com.food.kart.model.Restaurant;
import com.food.kart.storage.RestaurantStorage;

import java.util.List;

public class RestaurantService {

    public void updateQuantity(String restaurantName, Integer quantity) {
        Restaurant restaurant = RestaurantStorage.getRestaurant(RestaurantStorage.getRestaurantId(restaurantName));
        String dishName = restaurant.getDishes().get(0).getName();
        restaurant.updateQuantiy(dishName, quantity);
        System.out.println("Quantity successfully updated!");
    }

    public void showAllRestaurants(String pincode) {
        List<Restaurant> restaurantList = RestaurantStorage.getAllRestaurantsByPinCode(pincode);
        for(Restaurant restaurant : restaurantList) {
            System.out.println(restaurant.toString());
        }
    }
}
