package com.example.testDemo.modules.user.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private long userId;

    @Column(name = "stt")
    private int stt;

    @Column(name = "username")
    @NotEmpty(message = "Username không được để trống")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Mật khẩu không được để trống")
    private String password;

    @Column(name = "email")
    @NotEmpty(message = "Email không được để trống")
    @Email
    private String email;

    @Column(name = "phoneNumber")
    @NotEmpty(message = "Số điện thoại không được để trống")
    private String phoneNumber;

    @Column(name = "age")
    private int age;

    @Column(name = "role")
    private String role;
}