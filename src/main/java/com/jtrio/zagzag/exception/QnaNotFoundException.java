package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class QnaNotFoundException extends ApiException {
    public QnaNotFoundException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
