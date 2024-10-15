package com.emart.exceptions;


import com.emart.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class GlobalException {

    //ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex){
       String customMessage = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(customMessage)
                .status(HttpStatus.NOT_FOUND).success(true).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, Object> response = new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        allErrors.stream().forEach(objectError -> {
            String defaultMessage = objectError.getDefaultMessage();
            String field = ((FieldError) objectError).getField();
            response.put(field, defaultMessage);
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}

