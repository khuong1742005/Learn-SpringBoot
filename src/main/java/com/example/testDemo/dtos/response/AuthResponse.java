package com.example.testDemo.dtos.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AuthResponse {
    private boolean authenticated;
}
