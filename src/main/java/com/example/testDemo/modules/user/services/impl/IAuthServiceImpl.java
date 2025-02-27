package com.example.testDemo.modules.user.services.impl;

import com.example.testDemo.modules.user.entities.User;
import com.example.testDemo.modules.user.services.interfaces.IAuthService;
import org.springframework.stereotype.Service;

@Service
public class IAuthServiceImpl implements IAuthService {
    private final IUserServiceImpl userService;

    public IAuthServiceImpl(IUserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean authenticate(String email, String password) {
        for(User user : userService.getUsers()){
            if (user.getEmail().equals(email)){
                if (user.getPassword().equals(password)) return true;
            }
        }
        return false;
    }
}
