package com.example.springboot_mongodb;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
