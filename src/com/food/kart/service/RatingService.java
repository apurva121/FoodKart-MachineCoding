package com.food.kart.service;

import com.food.kart.model.Order;

import com.food.kart.model.Rating;
import com.food.kart.model.Restaurant;
import com.food.kart.storage.RestaurantStorage;
import com.food.kart.storage.UserStorage;

import java.util.List;

public class RatingService {

    public void addReview(String userName, String restaurantName, Double rating) {

        if(!checkIsValidUser(userName, restaurantName)) {
            System.out.println("can’t post a review, without ordering");
        }
        Restaurant restaurant = RestaurantStorage.getRestaurant(restaurantName);
        double avgRating = restaurant.getRating().getAvgRating();
        int count = restaurant.getRating().getCount();

        double newRating = (avgRating*count + rating)/(count+1);
        Rating updatedRating = new Rating(newRating, count+1);
        restaurant.setRating(updatedRating);

        System.out.println("Rating posted for " + restaurantName);

    }

    private boolean checkIsValidUser(String userName, String restaurantName) {

        List<Order> orders = UserStorage.orderHistory.get(UserStorage.getUserId(userName));

        for(Order order : orders) {
            if(restaurantName.equals(RestaurantStorage.restaurants.get(order.getRestaurantId()).getName())) {
                return true;
            }
        }
        return false;
    }
}
