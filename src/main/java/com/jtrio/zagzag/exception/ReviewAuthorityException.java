package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class ReviewAuthorityException extends ApiException{
    public ReviewAuthorityException(String msg){
        super(msg);
    }
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
