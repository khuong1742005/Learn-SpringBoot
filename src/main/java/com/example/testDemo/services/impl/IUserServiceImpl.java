package com.example.testDemo.services.impl;

import com.example.testDemo.dtos.requests.UserCreationRequest;
import com.example.testDemo.dtos.requests.UserUpdateRequest;
import com.example.testDemo.enums.Role;
import com.example.testDemo.repositories.UserRepository;
import com.example.testDemo.entities.User;
import com.example.testDemo.services.interfaces.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class IUserServiceImpl implements IUserService {
    UserRepository userRepository;
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserCreationRequest request) {
        if (userRepository.existsUserByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsUserByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsUserByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists");
        }
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER.name());
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUserById(String id, UserUpdateRequest request) {
        User user = getUserById(id);
        if (request.getPassword() != null) user.setPassword(request.getPassword());
        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getEmail() != null) {
            if (userRepository.existsUserByEmail(request.getEmail())) {
                throw new RuntimeException("Email already exists");
            }
            user.setEmail(request.getEmail());
        }
        if (request.getPhoneNumber() != null) {
            if (userRepository.existsUserByPhoneNumber(request.getPhoneNumber())) {
                throw new RuntimeException("Phone number already exists");
            }
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getAge() != 0) user.setAge(request.getAge());

        userRepository.save(user);
        return user;
    }

    @Override
    public boolean deleteUserById(String id) {
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(id);
        return true;
    }
}
