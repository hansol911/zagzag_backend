package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends ApiException {
    public CategoryNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
