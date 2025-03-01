package com.example.testDemo.controller;

import com.example.testDemo.dtos.response.ApiResponse;
import com.example.testDemo.dtos.requests.UserCreationRequest;
import com.example.testDemo.dtos.requests.UserUpdateRequest;
import com.example.testDemo.entities.User;
import com.example.testDemo.helpers.CodeStatus;
import com.example.testDemo.services.impl.IUserServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserServiceImpl userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiRespons = new ApiResponse<>();
        apiRespons.setCode(CodeStatus.USER_CREATED_SUCCESS.getCode());
        apiRespons.setResult(userService.createUser(request));
        return apiRespons;
    }

    @GetMapping
    public ApiResponse<List<User>> getUsers() {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(CodeStatus.USER_GET_SUCCESS.getCode());
        apiResponse.setResult(userService.getUsers());
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable("id") String id) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setCode(CodeStatus.USER_GET_SUCCESS.getCode());
        apiResponse.setResult(userService.getUserById(id));
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable("id") String id, @RequestBody @Valid UserUpdateRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setCode(CodeStatus.USER_UPDATED_SUCCESS.getCode());
        apiResponse.setResult(userService.updateUserById(id, request));
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUserById(@PathVariable("id") String id) {
        ApiResponse apiResponse = new ApiResponse<>();
        if (userService.deleteUserById(id)) {
            apiResponse.setCode(CodeStatus.USER_DELETE_SUCCESS.getCode());
            apiResponse.setMessage(CodeStatus.USER_DELETE_SUCCESS.getMessage());
            return apiResponse;
        }
        apiResponse.setCode(CodeStatus.USER_DELETE_FAILED.getCode());
        apiResponse.setMessage(CodeStatus.USER_DELETE_FAILED.getMessage());
        return apiResponse;
    }
}
