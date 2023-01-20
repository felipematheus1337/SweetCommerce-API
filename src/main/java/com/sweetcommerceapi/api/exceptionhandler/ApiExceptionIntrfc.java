package com.sweetcommerceapi.api.exceptionhandler;

import com.sweetcommerceapi.domain.exception.BusinessException;
import com.sweetcommerceapi.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public interface ApiExceptionIntrfc {
    ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                        HttpHeaders headers, HttpStatus status, WebRequest request);

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request);

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request);
}
