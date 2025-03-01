package com.example.testDemo.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserUpdateRequest {
    @Size(min = 8, message = "Password must be at least 8 chareacters")
    private String password;
    private String firstName;
    private String lastName;
    @Email(message = "Email not valid")
    private String email;
    @Size(min = 9, max = 10, message = "Phone number not valid")
    private String phoneNumber;
    @Min(value = 16, message = "You must be at least 16 years old")
    private int age;
}
