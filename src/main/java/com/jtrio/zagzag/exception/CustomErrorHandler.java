package com.jtrio.zagzag.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //Bean으로 띄우는 역할
public class CustomErrorHandler {
    @ExceptionHandler(ApiException.class) //exception 찾아서 가도록
    public ResponseEntity handleError(ApiException e) {
        return new ResponseEntity(e.getStatus());
    }
}
