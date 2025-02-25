package learn_Spring.demo.modules.user.controllers;

import jakarta.validation.Valid;
import learn_Spring.demo.modules.user.dtos.UserDTO;
import learn_Spring.demo.modules.user.entities.User;
import learn_Spring.demo.modules.user.services.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1")
public class WebController {
    private final ModelAndView modelAndView;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private ArrayList<UserDTO> users = new ArrayList<>();

    public WebController(ModelAndView modelAndView, ModelMapper modelMapper, UserService userService) {
        this.modelAndView = modelAndView;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/home")
    public ModelAndView homePage() {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/showUser")
    public ModelAndView getUser() {
        modelAndView.setViewName("showUser");
        modelAndView.addObject("message", "List users: ");
        ArrayList<User> usersRepository = userService.getUsers();
        users.clear();
        int count = 0;
        for (User user : usersRepository) {
            user.setId(++count);
            users.add(modelMapper.map(user, UserDTO.class));
        }
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/addUser")
    public ModelAndView addUser() {
        modelAndView.setViewName("addUser");
        modelAndView.addObject("message", "Thêm user");
        modelAndView.addObject("user", new UserDTO());
        return modelAndView;
    }

    @PostMapping("/addUser")
    public ModelAndView addUser(@ModelAttribute @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Lỗi, thêm user thất bại!");
            modelAndView.addObject("returnLink", "/v1/addUser");
            modelAndView.addObject("returnPage", "Quay lại trang thêm");
            return modelAndView;
        }
        modelAndView.setViewName("successAddUser");
        userDTO.setId(users.size() + 1);
        userService.addUser(userDTO);
        modelAndView.addObject("message", "Thêm user thành công!");
        return modelAndView;
    }

    @GetMapping("/deleteUser")
    public ModelAndView deleteUser() {
        modelAndView.setViewName("deleteUser");
        modelAndView.addObject("message", "Nhập id của user muốn xóa");
        return modelAndView;
    }

    @PostMapping("/deleteUser")
    public ModelAndView deleteUser(@RequestParam("id") int id){
        if (userService.getUserById(id) == null){
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Lỗi, xóa user thất bại!");
            modelAndView.addObject("returnLink", "/v1/deleteUser");
            modelAndView.addObject("returnPage", "Quay lại trang xóa");
            return modelAndView;
        }
        modelAndView.setViewName("successDeleteUser");
        userService.deleteUserById(id);
        modelAndView.addObject("message", "Xóa thành công");
        return modelAndView;
    }
}
