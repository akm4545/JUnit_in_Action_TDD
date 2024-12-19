package com.example.chapter18rest.exceptions;

public class PassengerNotFoundException extends RuntimeException{

    public PassengerNotFoundException(Long id){
        super("Passenger id not found : " + id);
    }
}
