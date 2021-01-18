package com.david.SpringSecurityWithH2.exceptions;

public class InvalidDate extends RuntimeException{
    private String invalidDate;
    public InvalidDate(String invalidDate){
        super(invalidDate);
        this.invalidDate = invalidDate;
    }
}