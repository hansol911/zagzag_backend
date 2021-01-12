package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class ReviewNotFoundException extends ApiException {
    public ReviewNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}