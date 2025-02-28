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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    @NotEmpty(message = "Username không được để trống")
    private String username;

    @Column(name = "first_name")
    @NotEmpty(message = "Tên không được để trống")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Họ không được để trống")
    private String lastName;

    @Column(name = "password")
    @NotEmpty(message = "Mật khẩu không được để trống")
    private String password;

    @Column(name = "email")
    @NotEmpty(message = "Email không được để trống")
    @Email
    private String email;

    @Column(name = "phone_number")
    @NotEmpty(message = "Số điện thoại không được để trống")
    private String phoneNumber;

    @Column(name = "age")
    private int age;

    @Column(name = "role")
    private String role;
}