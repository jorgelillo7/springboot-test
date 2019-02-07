package com.jlillo.tutorial.exception.advice;

import com.jlillo.tutorial.model.ApiErrorResponse;
import com.jlillo.tutorial.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFoundException(StudentNotFoundException se) {
        return new ApiErrorResponse("-1", se.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleException(Exception ex) {
        return new ApiErrorResponse("-2", ex.getMessage());

    }
}