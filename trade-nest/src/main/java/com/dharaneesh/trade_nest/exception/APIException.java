package com.dharaneesh.trade_nest.exception;

public class APIException extends RuntimeException{

    String message;

    public APIException(String message) {
        super(message);
        this.message = message;
    }
}
