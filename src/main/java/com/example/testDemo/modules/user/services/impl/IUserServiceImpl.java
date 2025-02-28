package com.example.testDemo.modules.user.services.impl;

import com.example.testDemo.modules.user.dtos.requests.UserCreationRequest;
import com.example.testDemo.modules.user.dtos.requests.UserUpdateRequest;
import com.example.testDemo.modules.user.repositories.UserRepository;
import com.example.testDemo.modules.user.entities.User;
import com.example.testDemo.modules.user.services.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean createUser(UserCreationRequest request){
        User user = modelMapper.map(request, User.class);
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public boolean updateUserById(String id, UserUpdateRequest request){
        User user = getUserById(id);
        if (request.getPassword() != null) user.setPassword(request.getPassword());
        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getPhoneNumber() != null) user.setPhoneNumber(request.getPhoneNumber());
        if (request.getAge() != 0) user.setAge(request.getAge());

        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUserById(String id){
        if (!userRepository.existsById(id)) return false;
        userRepository.deleteById(id);
        return true;
    }
}
