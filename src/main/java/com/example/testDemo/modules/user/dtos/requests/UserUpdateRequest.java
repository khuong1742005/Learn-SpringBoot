package com.example.testDemo.modules.user.dtos.requests;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int age;
}
