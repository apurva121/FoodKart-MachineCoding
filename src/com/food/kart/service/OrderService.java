package com.food.kart.service;

import com.food.kart.model.Order;
import com.food.kart.model.Restaurant;
import com.food.kart.model.User;
import com.food.kart.storage.RestaurantStorage;
import com.food.kart.storage.UserStorage;

import java.util.UUID;

public class OrderService {

    public void placeOrder(String userName, String restaurantName, Integer quantity){

        if(!isValidOrder(userName, restaurantName, quantity)){
            System.out.println("This order can not be placed");
        } else {

            Restaurant restaurant = RestaurantStorage.getRestaurant(restaurantName);
            User user = UserStorage.getUser(userName);

            String dishName = restaurant.getDishes().get(0).getName();

            Integer currentQuantity = restaurant.getDishesToQuantity().get(dishName);

            restaurant.updateQuantiy(dishName, currentQuantity - quantity);

            String orderId = UUID.randomUUID().toString();
            Order order = new Order(orderId, user.getId(), restaurant.getId(), quantity * (restaurant.getDishes().get(0).getPrice()));
            UserStorage.updateOrderHistory(user.getId(), order);

            System.out.println("Order Placed Successfully!");
        }
    }

    private boolean isValidOrder(String userName, String restaurantName, Integer quantity) {

        String pincode = UserStorage.getUser(userName).getPinCode();

        Restaurant restaurant = RestaurantStorage.getRestaurant(restaurantName);

        if(!restaurant.getServiceablePincodes().contains(pincode)) {
            return false;
        }

        if(restaurant.getDishesToQuantity().get(restaurant.getDishes().get(0).getName()) < quantity ){
            return false;
        }

        return true;
    }
}
