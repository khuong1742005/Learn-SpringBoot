package com.example.testDemo.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequest {
    @NotEmpty(message = "Username must not be empty")
    @Size(min = 6, message = "Username must be at least 6 chareacters")
    String username;
    @NotEmpty(message = "Password must not be empty")
    @Size(min = 8, message = "Password must be at least 8 chareacters")
    String password;
}
