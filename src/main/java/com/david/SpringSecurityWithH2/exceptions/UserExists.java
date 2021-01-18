package com.david.SpringSecurityWithH2.exceptions;

public class UserExists extends RuntimeException{
    private String userExists;
    public UserExists(String userExists){
        super(userExists);
        this.userExists = userExists;
    }
}
