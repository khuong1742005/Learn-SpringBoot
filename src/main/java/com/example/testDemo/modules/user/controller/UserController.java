package com.example.testDemo.modules.user.controller;

import com.example.testDemo.modules.user.dtos.requests.UserCreationRequest;
import com.example.testDemo.modules.user.dtos.requests.UserUpdateRequest;
import com.example.testDemo.modules.user.entities.User;
import com.example.testDemo.modules.user.services.impl.IUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserServiceImpl userService;

    @PostMapping
    public String createUser(@RequestBody UserCreationRequest request) {
        if (userService.createUser(request))
            return "User created successfully!";
        return "User created failed!";
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable("id") String id, @RequestBody UserUpdateRequest request) {
        if (userService.updateUserById(id, request))
            return "User updated successfully!";
        return "User update failed!";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") String id) {
        if (userService.deleteUserById(id))
            return "User deleted successfully!";
        return "User delete failed!";
    }
}
