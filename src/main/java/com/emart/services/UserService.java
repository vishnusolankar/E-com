package com.emart.services;

import com.emart.dto.UserDto;
import com.emart.payloads.PageableResponse;

import java.util.List;

public interface UserService {

    //CREATE USER
    UserDto createUser(UserDto userDto);

    //UPDATE USER
    UserDto updateUserById(UserDto userDto, String userId);

    //GET USER BY ID
    UserDto getUserById(String userId);

    //GET USER BY EMAIL
    UserDto getUserByEmail(String email);

    //GET ALL USERS
    PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);

    //DELETE USER BY ID
    void deleteUser(String userId);

    //SEARCH USER
    List<UserDto> searchUser(String keyword);

}
