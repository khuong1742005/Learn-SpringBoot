package com.example.testDemo.modules.user.services.interfaces;

import com.example.testDemo.modules.user.entities.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    boolean addUser(User user);
    User getUserById(int id);
    boolean deleteUserById(int id);
}
