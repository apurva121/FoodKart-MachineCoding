package com.food.kart.exception;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String message){
        super(message);
    }

    public InvalidDataException(String message, Exception exception) {
        super(message, exception);
    }

}
