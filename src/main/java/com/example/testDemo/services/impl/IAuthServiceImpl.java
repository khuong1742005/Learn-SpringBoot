package com.example.testDemo.services.impl;

import com.example.testDemo.dtos.requests.AuthRequest;
import com.example.testDemo.repositories.UserRepository;
import com.example.testDemo.services.interfaces.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IAuthServiceImpl implements IAuthService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean authenticate(AuthRequest request) {
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found!"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
