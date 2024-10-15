package com.emart.payloads;


import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse
{
    private String message;
    private HttpStatus status;
    private boolean success;


    // Constructor to be used when user is not found
    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

//    // Constructor to be used for successful operation
//    public ApiResponse(String message, HttpStatus status, boolean success) {
//        this.message = message;
//        this.status = status;
//        this.success = success;
//    }
}

