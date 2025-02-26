package learn_Spring.demo.modules.user.services.interfaces;

import learn_Spring.demo.modules.user.dtos.UserDTO;
import learn_Spring.demo.modules.user.entities.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    boolean addUser(UserDTO userDTO);
    User getUserById(int id);
    boolean deleteUserById(int id);
}
