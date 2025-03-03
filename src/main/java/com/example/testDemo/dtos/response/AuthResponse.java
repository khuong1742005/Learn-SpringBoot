package com.example.testDemo.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
    private boolean authenticated;
}
