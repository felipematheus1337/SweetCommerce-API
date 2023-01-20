package com.sweetcommerceapi.api.exceptionhandler;

import com.sweetcommerceapi.domain.exception.BusinessException;
import com.sweetcommerceapi.domain.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler implements ApiExceptionIntrfc {

    private MessageSource messageSource;
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {
        Optional<List<Problem.Field>> fields = Optional.empty();


        ex.getBindingResult().getAllErrors()
                .forEach(error ->
                        fields.get().add(new Problem.Field(
                                ((FieldError) error).getField(),error.getDefaultMessage())));

        var problem = runnerUpProblem(HttpStatus.BAD_REQUEST,
                "One or more fields are invalid!",fields);


        return handleExceptionInternal(ex, problem,headers,status,request);
    }

    @Override
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request) {
        var problem = runnerUpProblem(HttpStatus.BAD_REQUEST,ex.getMessage(), Optional.empty());
        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.valueOf(problem.getStatus()), request);
    }

    @Override
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        var problem = runnerUpProblem(HttpStatus.NOT_FOUND,ex.getMessage(), Optional.empty());
        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.valueOf(problem.getStatus()), request);

    }

    private static Problem runnerUpProblem(HttpStatus status,String title, Optional<List<Problem.Field>> fields) {
        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setHourDate(OffsetDateTime.now());
        problem.setTitle(title);
        fields.ifPresent(problem::setFields);
        return problem;
    }

}
