package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class ReviewRightException extends ApiException{
    public ReviewRightException (String msg){
        super(msg);
    }
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
