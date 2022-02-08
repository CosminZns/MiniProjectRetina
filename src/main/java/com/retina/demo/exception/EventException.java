package com.retina.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EventException extends RuntimeException {

    public EventException(String message) {
        super(message);
    }
}
