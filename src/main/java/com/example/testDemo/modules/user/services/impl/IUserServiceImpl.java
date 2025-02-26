package com.example.testDemo.modules.user.services.impl;

import com.example.testDemo.modules.user.repositories.UserRepository;
import com.example.testDemo.modules.user.entities.User;
import com.example.testDemo.modules.user.services.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    public IUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        int countId = 0;
        for(User u : users){
            u.setStt(++countId);
            userRepository.save(u);
        }
        return users;
    }

    @Override
    public boolean addUser(User user) {
        user.setRole("USER");
        userRepository.save(user);
        return true;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteUserById(int stt) {
        if (userRepository.findByStt(stt) == null) return false;
        userRepository.delete(userRepository.findByStt(stt));
        return true;
    }
}
