package com.food.kart.storage;

import com.food.kart.exception.InvalidDataException;
import com.food.kart.model.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RestaurantStorage {

    public final static Map<String, Restaurant> restaurants = new HashMap<>();
    public final static Map<String, List<Restaurant>> restaurantsByPincode = new HashMap<>();
    public final static Map<String, String> restaurantNameToId = new HashMap<>();


    public static void registerRestaurant(String restaurantId, Restaurant restaurant) {
        restaurants.put(restaurantId, restaurant);
        restaurantNameToId.put(restaurant.getName(), restaurantId);

        for(String pincode : restaurant.getServiceablePincodes()) {
            if(restaurantsByPincode.containsKey(pincode)) {
                restaurantsByPincode.get(pincode).add(restaurant);
            } else {
                List<Restaurant> restaurants = new ArrayList<>();
                restaurants.add(restaurant);
                restaurantsByPincode.put(pincode, restaurants);
            }
        }
    }

    public static Restaurant getRestaurant(String restaurantName) {
       return restaurants.get(getRestaurantId(restaurantName));
    }


    public static List<Restaurant> getAllRestaurantsByPinCode(String pincode) {

        if(!restaurantsByPincode.containsKey(pincode)) {
            return new ArrayList<>();
        }

        return restaurantsByPincode.get(pincode);
    }

    public static String getRestaurantId(String restaurantName) {
        if(!restaurantNameToId.containsKey(restaurantName)){
            throw new InvalidDataException("Restaurant name is not present");
        }
        return restaurantNameToId.get(restaurantName);
    }

}
