package learn_Spring.demo.modules.user.services.impl;

import learn_Spring.demo.base.BaseService;
import learn_Spring.demo.modules.user.dtos.UserDTO;
import learn_Spring.demo.modules.user.entities.User;
import learn_Spring.demo.modules.user.services.interfaces.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService extends BaseService implements UserServiceInterface {
    private final ModelMapper modelMapper;
    ArrayList<User> usersRepository = new ArrayList<>();

    public UserService(ModelMapper modelMapper) {
        super();
        this.modelMapper = modelMapper;
        usersRepository.add(new User(001, "Pham", "Khuong", 18, "khuong@gmail.com", "Male"));
        usersRepository.add(new User(002, "Dinh", "Vuong", 18, "vuong@gmail.com", "Male"));
        usersRepository.add(new User(003, "Nguyen", "Dat", 18, "dat@gmail.com", "Male"));
        usersRepository.add(new User(004, "Giang", "Chinh", 18, "chinh@gmail.com", "Male"));
    }

    @Override
    public ArrayList<User> getUsers() {
        return usersRepository;
    }

    @Override
    public void addUser(UserDTO userDTO){
        usersRepository.add(modelMapper.map(userDTO, User.class));
    }

    @Override
    public User getUserById(int id){
        for(User user : usersRepository){
            if (user.getId() == id) return user;
        }
        return null;
    }

    @Override
    public void deleteUserById(int id){
        usersRepository.remove(getUserById(id));
    }
}
