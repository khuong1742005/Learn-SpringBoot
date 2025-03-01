package com.example.testDemo.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserCreationRequest {
    @NotEmpty(message = "Username must not be empty")
    @Size(min = 6, message = "Username must be at least 6 chareacters")
    private String username;
    @NotEmpty(message = "Password must not be empty")
    @Size(min = 8, message = "Password must be at least 8 chareacters")
    private String password;
    @NotEmpty(message = "First name must not be empty")
    private String firstName;
    @NotEmpty(message = "Last name must not be empty")
    private String lastName;
    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email not valid")
    private String email;
    @NotEmpty(message = "Phone number must not be empty")
    @Size(min = 9, max = 10, message = "Phone number not valid")
    private String phoneNumber;
    @NotNull(message = "Age must not be empty")
    @Min(value = 16, message = "You must be at least 16 years old")
    private int age;
}
