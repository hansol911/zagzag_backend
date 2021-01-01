package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class ProductLackException extends ApiException{
    public ProductLackException (String msg){
        super(msg);
    }
    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
