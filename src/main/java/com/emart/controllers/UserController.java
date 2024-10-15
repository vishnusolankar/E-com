package com.emart.controllers;

import com.emart.config.AppConstants;
import com.emart.dto.UserDto;
import com.emart.payloads.ApiResponse;
import com.emart.payloads.PageableResponse;
import com.emart.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Component
//@Service
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto newUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserById(@Valid @RequestBody UserDto userDto, @PathVariable String userId) {
        UserDto updatedUserDto = this.userService.updateUserById(userDto, userId);
        return new ResponseEntity<UserDto>(updatedUserDto, HttpStatus.OK);
    }

    @GetMapping("/getById/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        UserDto userById = this.userService.getUserById(userId);
        return new ResponseEntity<UserDto>(userById, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam(value ="pageNumber", defaultValue= AppConstants.PAGE_NUMBER, required=false) int pageNumber,
            @RequestParam(value ="pageSize", defaultValue=AppConstants.PAGE_SIZE, required=false) int pageSize,
            @RequestParam(value ="SortBy", defaultValue=AppConstants.SORT_BY, required=false) String sortBy,
            @RequestParam(value ="SortDir", defaultValue=AppConstants.SORT_DIR, required=false) String sortDir
    ) {
        PageableResponse<UserDto> allUsers = this.userService.getAllUsers(pageNumber, pageSize,sortBy, sortDir);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable String userId){
        this.userService.deleteUser(userId);
         String message = "User deleted successfully with userId: " + userId;
        return new ResponseEntity<ApiResponse>(
                new ApiResponse(message, HttpStatus.OK, true), HttpStatus.OK
        );
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        UserDto userByEmail = this.userService.getUserByEmail(email);
        return new ResponseEntity<>(userByEmail, HttpStatus.OK);
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<UserDto>> searchUsersByKeyword(@PathVariable String keyword){
        List<UserDto> userDtos = this.userService.searchUser(keyword);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

}