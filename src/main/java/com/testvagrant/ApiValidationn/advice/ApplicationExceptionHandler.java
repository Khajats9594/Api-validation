package com.testvagrant.ApiValidationn.advice;

import com.testvagrant.ApiValidationn.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception){
        Map<String,String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String> handleBusinessException(UserNotFoundException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return errorMap;
    }
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, ConstraintViolationException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleRequestPathValidationException(Exception ex, HttpServletRequest request){
        Map<String,Object> errorMap = new HashMap<>();
        errorMap.put("timestamp",new Date());
        errorMap.put("status",HttpStatus.BAD_REQUEST);
        errorMap.put("error",ex.getMessage());
        errorMap.put("path",request.getServletPath());
        return errorMap;
    }
}
