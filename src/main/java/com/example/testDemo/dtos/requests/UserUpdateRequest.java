package com.example.testDemo.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Size(min = 8, message = "Password must be at least 8 chareacters")
    String password;
    String firstName;
    String lastName;
    @Email(message = "Email not valid")
    String email;
    @Size(min = 9, max = 10, message = "Phone number not valid")
    String phoneNumber;
    @Min(value = 16, message = "You must be at least 16 years old")
    int age;
}
