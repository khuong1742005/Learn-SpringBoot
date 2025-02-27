package com.example.testDemo.modules.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthUserDTO {
    @NotEmpty(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotEmpty
    @Size(min = 8, max = 100)
    private String password;

    @NotEmpty
    @Size(min = 8, max = 100)
    private String confirmPassword;
}
