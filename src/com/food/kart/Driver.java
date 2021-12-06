package com.food.kart;

import com.food.kart.model.Dish;
import com.food.kart.service.OrderService;
import com.food.kart.service.RatingService;
import com.food.kart.service.RegistrationService;
import com.food.kart.service.RestaurantService;

import java.util.*;

public class Driver {

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        RatingService ratingService = new RatingService();
        RegistrationService registrationService = new RegistrationService();
        RestaurantService restaurantService = new RestaurantService();

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();

            String[] inputs = input.split(" ");

            String restaurantName, userName;

            switch(inputs[0]) {
                case "register_user" :
                    registrationService.registerUser(inputs[1], inputs[2], inputs[3]);
                    break;
                case "register_restaurant" :
                    restaurantName = inputs[1];
                    List<String> pincodes = Arrays.asList(inputs[2].split("_"));
                    String dishName = inputs[3];
                    Double price = new Double(inputs[4]);
                    Integer quantity = new Integer(inputs[5]);
                    List<Dish> dishes = new ArrayList<>();
                    dishes.add(new Dish(dishName, price));
                    Map<String, Integer> dishesToQuantity = new HashMap<>();
                    dishesToQuantity.put(dishName, quantity);
                    registrationService.registerRestaurant(restaurantName, pincodes, dishesToQuantity, dishes);
                    break;
                case "place_order":
                    userName = inputs[1];
                    restaurantName = inputs[2];
                    Integer q = new Integer(inputs[3]);
                    orderService.placeOrder(userName, restaurantName, q);
                    break;

                case "create_review" :
                    userName = inputs[1];
                    restaurantName = inputs[2];
                    Double rating = new Double(inputs[3]);
                    ratingService.addReview(userName, restaurantName, rating);
                    break;
                case "show_restaurant" :
                    String pincode = inputs[1];
                    restaurantService.showAllRestaurants(pincode);
                    break;
                case "update_quantity" :
                    restaurantName = inputs[1];
                    Integer qty = new Integer(inputs[2]);

                    restaurantService.updateQuantity(restaurantName, qty);
                    break;

                default:
                    System.out.println("Not a valid input choice!");
            }
        }
    }
}
