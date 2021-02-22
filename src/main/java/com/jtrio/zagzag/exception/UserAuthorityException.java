package com.jtrio.zagzag.exception;

import org.springframework.http.HttpStatus;

public class UserAuthorityException extends ApiException {
    public UserAuthorityException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}