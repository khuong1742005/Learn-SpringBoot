package com.example.testDemo.controller;

import com.example.testDemo.dtos.response.ApiResponse;
import com.example.testDemo.dtos.requests.UserCreationRequest;
import com.example.testDemo.dtos.requests.UserUpdateRequest;
import com.example.testDemo.dtos.response.UserResponse;
import com.example.testDemo.entities.User;
import com.example.testDemo.helpers.CodeStatus;
import com.example.testDemo.services.impl.IUserServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    IUserServiceImpl userService;
    ModelMapper modelMapper;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiRespons = new ApiResponse<>();
        apiRespons.setCode(CodeStatus.USER_CREATED_SUCCESS.getCode());
        apiRespons.setResult(modelMapper.map(userService.createUser(request), UserResponse.class));
        return apiRespons;
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        apiResponse.setCode(CodeStatus.USER_GET_SUCCESS.getCode());
        List<UserResponse> userResponses = userService.getUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
        apiResponse.setResult(userResponses);
        return apiResponse;
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable("id") String id) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(CodeStatus.USER_GET_SUCCESS.getCode());
        apiResponse.setResult(modelMapper.map(userService.getUserById(id), UserResponse.class));
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

    @GetMapping("/my-info")
    public ApiResponse<UserResponse> getMyInfo(){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(CodeStatus.USER_GET_SUCCESS.getCode());
        apiResponse.setResult(modelMapper.map(userService.getMyInfo(), UserResponse.class));
        return apiResponse;
    }
}
