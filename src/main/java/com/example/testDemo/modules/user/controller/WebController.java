package com.example.testDemo.modules.user.controller;

import com.example.testDemo.modules.user.dtos.AuthUserDTO;
import com.example.testDemo.modules.user.services.impl.IAuthServiceImpl;
import jakarta.validation.Valid;
import com.example.testDemo.modules.user.dtos.UserDTO;
import com.example.testDemo.modules.user.entities.User;
import com.example.testDemo.modules.user.services.impl.IUserServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class WebController {
    private final ModelAndView modelAndView;
    private final ModelMapper modelMapper;
    private final IUserServiceImpl userService;
    private final IAuthServiceImpl authService;

    public WebController(ModelAndView modelAndView, ModelMapper modelMapper, IUserServiceImpl userService, IAuthServiceImpl authService) {
        this.modelAndView = modelAndView;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.authService = authService;
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
        modelAndView.addObject("users", modelMapper.map(userService.getUsers(), new TypeToken<List<UserDTO>>(){}.getType()));
        return modelAndView;
    }

    @GetMapping("/addUser")
    public ModelAndView addUser() {
        modelAndView.setViewName("addUser");
        modelAndView.addObject("message", "Thêm user");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/addUser")
    public ModelAndView addUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Lỗi, thêm user thất bại!");
            modelAndView.addObject("returnLink", "/v1/addUser");
            modelAndView.addObject("returnPage", "Quay lại trang thêm");
            return modelAndView;
        }
        modelAndView.setViewName("successAddUser");
        userService.addUser(user);
        modelAndView.addObject("message", "Thêm user thành công!");
        return modelAndView;
    }

    @GetMapping("/deleteUser")
    public ModelAndView deleteUser() {
        modelAndView.setViewName("deleteUser");
        modelAndView.addObject("message", "Nhập stt của user muốn xóa");
        return modelAndView;
    }

    @PostMapping("/deleteUser")
    public ModelAndView deleteUser(@RequestParam("id") int id) {
        if (!userService.deleteUserById(id)) {
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

    @GetMapping("/auth/login")
    public ModelAndView getAuthLoginView(){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/auth/login")
    public ModelAndView authLoginUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password){
        if (!authService.authenticate(email, password)){
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Lỗi, đăng nhập thất bại!");
            modelAndView.addObject("returnLink", "/v1/auth/login");
            modelAndView.addObject("returnPage", "Quay lại trang đăng nhập");
            return modelAndView;
        }
        modelAndView.setViewName("successAddUser");
        return modelAndView;
    }

    @GetMapping("/auth/signup")
    public ModelAndView getAuthSignUp(){
        modelAndView.setViewName("signup");
        modelAndView.addObject("user", new AuthUserDTO());
        return modelAndView;
    }

    @PostMapping("/auth/signup")
    public ModelAndView authSignUpUser(@ModelAttribute @Valid AuthUserDTO authUserDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors() || !authUserDTO.getPassword().equals(authUserDTO.getConfirmPassword())){
            modelAndView.setViewName("error");
            modelAndView.addObject("message", "Lỗi, đăng kí thất bại!");
            modelAndView.addObject("returnLink", "/v1/auth/signup");
            modelAndView.addObject("returnPage", "Quay lại trang đăng kí");
            return modelAndView;
        }
        modelAndView.setViewName("successAddUser");
        User user = modelMapper.map(authUserDTO, User.class);
        user.setStt(userService.getUsers().size()+1);
        user.setRole("USER");
        user.setPhoneNumber("RANDOM_PHONE_NUMBER");
        user.setAge(18);
        user.setUsername("RANDOM_USERNAME");
        userService.addUser(user);
        modelAndView.addObject("message", "Thêm user thành công!");
        return modelAndView;
    }
}
