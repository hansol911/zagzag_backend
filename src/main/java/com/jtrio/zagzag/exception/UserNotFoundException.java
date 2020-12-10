package com.jtrio.zagzag.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (String msg){
        super(msg);
    }
}
