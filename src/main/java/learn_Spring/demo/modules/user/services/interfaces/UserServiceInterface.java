package learn_Spring.demo.modules.user.services.interfaces;

import learn_Spring.demo.modules.user.dtos.UserDTO;
import learn_Spring.demo.modules.user.entities.User;

import java.util.ArrayList;

public interface UserServiceInterface {
    ArrayList<User> getUsers();
    void addUser(UserDTO userDTO);
    User getUserById(int id);
    void deleteUserById(int id);
}
