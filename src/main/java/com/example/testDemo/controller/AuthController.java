package com.example.testDemo.controller;

import com.example.testDemo.dtos.requests.AuthRequest;
import com.example.testDemo.dtos.response.ApiResponse;
import com.example.testDemo.dtos.response.AuthResponse;
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
    @Autowired
    AuthResponse authResponse;

    @PostMapping("/log-in")
    public ApiResponse<AuthResponse> authentication(@RequestBody AuthRequest request) {
        ApiResponse<AuthResponse> apiResponse = new ApiResponse<>();
        boolean result = authService.authenticate(request);
        if (result){
            authResponse.setAuthenticated(true);
        } else {
            apiResponse.setCode(400);
            authResponse.setAuthenticated(false);
        }
        apiResponse.setResult(authResponse);
        return apiResponse;
    }
}
