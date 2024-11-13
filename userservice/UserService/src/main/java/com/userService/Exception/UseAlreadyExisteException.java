package com.userService.Exception;

public class UseAlreadyExisteException extends RuntimeException {
    public UseAlreadyExisteException(String s){
        super("User already exist");
    }
}
