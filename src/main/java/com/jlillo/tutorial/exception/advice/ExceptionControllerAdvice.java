package com.jlillo.tutorial.exception.advice;

import com.jlillo.tutorial.model.ApiErrorResponse;
import com.jlillo.tutorial.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.UnknownHostException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFoundException(NotFoundException se) {
        return new ApiErrorResponse("-1", se.getMessage());
    }

    @ExceptionHandler(UnknownHostException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleException(Exception ex) {
        return new ApiErrorResponse("-2", ex.getMessage());

    }
}