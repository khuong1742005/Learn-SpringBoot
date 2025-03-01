package com.example.testDemo.controller;

import com.example.testDemo.dtos.response.ApiResponse;
import com.example.testDemo.dtos.requests.UserCreationRequest;
import com.example.testDemo.dtos.requests.UserUpdateRequest;
import com.example.testDemo.entities.User;
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
        apiRespons.setResult(userService.createUser(request));
        return apiRespons;
    }

    @GetMapping
    public ApiResponse<List<User>> getUsers() {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUsers());
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable("id") String id) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUserById(id));
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable("id") String id, @RequestBody @Valid UserUpdateRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUserById(id, request));
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteUserById(@PathVariable("id") String id) {
        ApiResponse apiResponse = new ApiResponse<>();
        if (userService.deleteUserById(id)) {
            apiResponse.setMessage("User deleted successfully!");
            return apiResponse;
        }
        apiResponse.setCode(400);
        apiResponse.setMessage("User deleted failed!");
        return apiResponse;
    }
}
