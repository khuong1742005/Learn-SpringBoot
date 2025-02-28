package com.example.testDemo.modules.user.services.interfaces;

import com.example.testDemo.modules.user.dtos.requests.UserCreationRequest;
import com.example.testDemo.modules.user.dtos.requests.UserUpdateRequest;
import com.example.testDemo.modules.user.entities.User;

import java.util.List;

public interface IUserService {
    boolean createUser(UserCreationRequest request);
    List<User> getUsers();
    User getUserById(String id);
    boolean updateUserById(String id, UserUpdateRequest request);
    boolean deleteUserById(String id);
}
