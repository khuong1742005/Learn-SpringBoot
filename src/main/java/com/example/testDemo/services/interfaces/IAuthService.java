package com.example.testDemo.services.interfaces;

import com.example.testDemo.dtos.requests.AuthRequest;

public interface IAuthService {
    boolean authenticate(AuthRequest request);
}
