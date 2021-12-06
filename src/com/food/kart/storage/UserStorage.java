package com.food.kart.storage;

import com.food.kart.exception.InvalidDataException;
import com.food.kart.model.Order;
import com.food.kart.model.Rating;
import com.food.kart.model.User;

import java.util.*;

public final class UserStorage {

    public final static Map<String, User> users = new HashMap<>();

    public final static Map<String, String> userNameToUserId = new HashMap<>();

    public final static Set<String> phoneNumbers = new HashSet<>();

    public final static Map<String, List<Order>> orderHistory = new HashMap<>();

    public final static Map<String, List<Rating>> ratingHistory = new HashMap<>();



    public static void addUser(String userId, User user) {
        users.put(userId, user);

        userNameToUserId.put(user.getName(), user.getId());

        phoneNumbers.add(user.getPhoneNumber());
    }

    public static User getUser(String userName) {
        return users.get(getUserId(userName));
    }

    public static String getUserId(String userName) {
        if(!userNameToUserId.containsKey(userName)){
            throw new InvalidDataException("User name is not present in the system");
        }

        return userNameToUserId.get(userName);
    }

    public static void updateOrderHistory(String userId, Order order) {
        if(orderHistory.containsKey(userId)){
            orderHistory.get(userId).add(order);
        }else{
            List<Order> orders = new ArrayList<>();
            orders.add(order);
            orderHistory.put(userId, orders);
        }
    }
}
