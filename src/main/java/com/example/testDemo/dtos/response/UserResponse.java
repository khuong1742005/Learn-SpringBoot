package com.example.testDemo.dtos.response;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    @NotBlank(message = "Username must not be empty")
    @Size(min = 6, message = "Username must be at least 6 chareacters")
    String username;
    @NotBlank(message = "First name must not be empty")
    String firstName;
    @NotBlank(message = "Last name must not be empty")
    String lastName;
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Email not valid")
    String email;
    @NotBlank(message = "Phone number must not be empty")
    @Size(min = 9, max = 10, message = "Phone number not valid")
    String phoneNumber;
    @NotNull(message = "Age must not be empty")
    @Min(value = 16, message = "You must be at least 16 years old")
    int age;
    String role;
}
