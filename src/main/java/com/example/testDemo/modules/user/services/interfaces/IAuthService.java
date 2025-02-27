package com.example.testDemo.modules.user.services.interfaces;

public interface IAuthService{
    boolean authenticate(String email, String password);
}
