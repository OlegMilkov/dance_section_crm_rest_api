package com.example.dance_section_crm_rest_api.exeption_handling;

public class NoSuchChildException extends RuntimeException{

    public NoSuchChildException(String message) {
        super(message);
    }
}
