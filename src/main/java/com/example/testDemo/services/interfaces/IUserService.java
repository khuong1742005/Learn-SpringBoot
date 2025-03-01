package com.example.testDemo.services.interfaces;

import com.example.testDemo.dtos.requests.UserCreationRequest;
import com.example.testDemo.dtos.requests.UserUpdateRequest;
import com.example.testDemo.entities.User;

import java.util.List;

public interface IUserService {
    User createUser(UserCreationRequest request);
    List<User> getUsers();
    User getUserById(String id);
    User updateUserById(String id, UserUpdateRequest request);
    boolean deleteUserById(String id);
}
