package com.food.kart.exception;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message){
        super(message);
    }

    public InvalidRequestException(String message, Exception exception) {
        super(message, exception);
    }
}
