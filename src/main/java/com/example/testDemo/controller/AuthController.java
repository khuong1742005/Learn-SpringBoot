package com.example.testDemo.controller;

import com.example.testDemo.dtos.requests.AuthRequest;
import com.example.testDemo.dtos.requests.IntrospectRequest;
import com.example.testDemo.dtos.response.ApiResponse;
import com.example.testDemo.dtos.response.AuthResponse;
import com.example.testDemo.dtos.response.IntrospectResponse;
import com.example.testDemo.helpers.CodeStatus;
import com.example.testDemo.services.impl.IAuthServiceImpl;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    IAuthServiceImpl authService;

    @PostMapping("/token")
    public ApiResponse<AuthResponse> authentication(@RequestBody AuthRequest request) {
        var result = authService.authenticate(request);
        return ApiResponse.<AuthResponse>builder()
                .result(result)
                .code(CodeStatus.USER_LOGIN_SUCCESS.getCode())
                .message(CodeStatus.USER_LOGIN_SUCCESS.getMessage())
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> verifyToken(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .code(CodeStatus.USER_LOGIN_SUCCESS.getCode())
                .build();
    }
}
