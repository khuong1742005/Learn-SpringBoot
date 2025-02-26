package learn_Spring.demo.modules.user.services.impl;

import learn_Spring.demo.base.BaseService;
import learn_Spring.demo.modules.user.dtos.UserDTO;
import learn_Spring.demo.modules.user.entities.User;
import learn_Spring.demo.modules.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IUserServiceImpl extends BaseService implements learn_Spring.demo.modules.user.services.interfaces.IUserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public IUserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        super();
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
        userRepository.save(modelMapper.map(userDTO, User.class));
        return true;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById((long) id).orElse(null);
    }

    @Override
    public boolean deleteUserById(int id) {
        if (userRepository.existsById((long) id)) {
            userRepository.deleteById((long) id);
            return true;
        } else return false;
    }
}
