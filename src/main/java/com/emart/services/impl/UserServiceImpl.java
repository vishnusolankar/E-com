package com.emart.services.impl;


import com.emart.dto.UserDto;
import com.emart.entities.User;
import com.emart.exceptions.ResourceNotFoundException;
import com.emart.helper.Helper;
import com.emart.payloads.PageableResponse;
import com.emart.repositories.UserRepository;
import com.emart.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        //to generate random,unique userId
        // String userId = UUID.randomUUID().toString();
        // userDto.setUserId(userId);
        // dto --> entity
        User user = dtoToEntity(userDto);
        User savedUser = this.userRepository.save(user);
        // entity --> dto
        UserDto newDto = entityToDto(savedUser);
        return newDto;
    }

    @Override
    public UserDto updateUserById(UserDto userDto, String userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userId: "+userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        //others
        User updatedUser = this.userRepository.save(user);
        UserDto updatedDto = entityToDto(updatedUser);
        return updatedDto;
    }

    @Override
    public UserDto getUserById(String userId) {
        User userById = this.userRepository.findById(userId)
                 .orElseThrow(() -> new ResourceNotFoundException("User not found with userId: " + userId));

        UserDto userDto = entityToDto(userById);
        return userDto;
    }

    @Override
    public PageableResponse<UserDto> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {
       // Sort sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Sort sort;
        if (sortDir.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else{
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);

        Page<User> page = this.userRepository.findAll(pageable);

//        List<User> users = page.getContent();
//        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
//        PageableResponse<UserDto> response = new PageableResponse<>();
//        response.setContent(dtoList);
//        response.setPageNumber(page.getNumber());
//        response.setPageSize(page.getSize());
//        response.setTotalElements(page.getTotalElements());
//        response.setTotalPages(page.getTotalPages());
//        response.setLastPage(page.isLast());
        //or
        PageableResponse<UserDto> pageableResponse = Helper.getPageableResponse(page, UserDto.class);
        return pageableResponse;
    }

    @Override
    public void deleteUser(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with userId: " + userId));
        this.userRepository.delete(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = this.userRepository.getUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return entityToDto(user);
    }

    public List<UserDto> searchUser(String keyword) {
        List<User> users = this.userRepository.findByNameContaining(keyword)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with keyword :" + keyword));
       // List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        List<UserDto> dtoList = users.stream().map(this::entityToDto).collect(Collectors.toList());
        return dtoList;
    }

    private User dtoToEntity(UserDto userDto) {
      /*User user = User.builder()
                        .userId(userDto.getUserId())
                        .name(userDto.getName())
                        .email(userDto.getEmail())
                        .password(userDto.getPassword())
                        .gender(userDto.getGender())
                        .about(userDto.getAbout())
                        .imageName(userDto.getImageName())
                        .build();
      return user;*/
        return modelMapper.map(userDto, User.class);
    }

    private UserDto entityToDto(User savedUser) {
      /*UserDto userDto = UserDto.builder()
                                .userId(savedUser.getUserId())
                                .name(savedUser.getName())
                                .email(savedUser.getEmail())
                                .password(savedUser.getPassword())
                                .gender(savedUser.getGender())
                                .about(savedUser.getAbout())
                                .imageName(savedUser.getImageName())
                                .build();
      return userDto;*/
        return modelMapper.map(savedUser, UserDto.class);
    }
}

