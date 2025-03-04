package com.example.testDemo.services.interfaces;

import com.example.testDemo.dtos.requests.AuthRequest;
import com.example.testDemo.dtos.response.AuthResponse;

public interface IAuthService {
    AuthResponse authenticate(AuthRequest request);
}
