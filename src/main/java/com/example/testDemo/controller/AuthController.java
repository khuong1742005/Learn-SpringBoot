package com.example.testDemo.controller;

import com.example.testDemo.dtos.requests.AuthRequest;
import com.example.testDemo.dtos.response.ApiResponse;
import com.example.testDemo.dtos.response.AuthResponse;
import com.example.testDemo.helpers.CodeStatus;
import com.example.testDemo.services.impl.IAuthServiceImpl;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Builder
public class AuthController {
    @Autowired
    IAuthServiceImpl authService;

    AuthResponse authResponse;

    @PostMapping("/log-in")
    public ApiResponse<AuthResponse> authentication(@RequestBody AuthRequest request) {
        ApiResponse<AuthResponse> apiResponse = new ApiResponse<>();
        boolean result = authService.authenticate(request);
        if (result){
            apiResponse.setCode(CodeStatus.USER_LOGIN_SUCCESS.getCode());
            apiResponse.setMessage(CodeStatus.USER_LOGIN_SUCCESS.getMessage());
            authResponse.setAuthenticated(true);
        } else {
            apiResponse.setCode(CodeStatus.USER_LOGIN_FAILED.getCode());
            apiResponse.setMessage(CodeStatus.USER_LOGIN_FAILED.getMessage());
            authResponse.setAuthenticated(false);
        }
        apiResponse.setResult(authResponse);
        return apiResponse;
    }
}
