package com.example.testDemo.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequest {
    @NotEmpty(message = "Username must not be empty")
    @Size(min = 6, message = "Username must be at least 6 chareacters")
    private String username;
    @NotEmpty(message = "Password must not be empty")
    @Size(min = 8, message = "Password must be at least 8 chareacters")
    private String password;
}
