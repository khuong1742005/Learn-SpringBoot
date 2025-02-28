package com.example.testDemo.modules.user.controller;

import com.example.testDemo.modules.user.entities.User;
import com.example.testDemo.modules.user.services.impl.IUserServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.example.testDemo.helpers.ErrorView.errorModel;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
//    private final ModelAndView modelAndView;
//    private final ModelMapper modelMapper;
//    private final IUserServiceImpl userService;
//    private final IAuthServiceImpl authService;
//
//    public AuthController(ModelAndView modelAndView, ModelMapper modelMapper, IUserServiceImpl userService, IAuthServiceImpl authService) {
//        this.modelAndView = modelAndView;
//        this.modelMapper = modelMapper;
//        this.userService = userService;
//        this.authService = authService;
//    }
//    @GetMapping("/login")
//    public ModelAndView getAuthLoginView(){
//        modelAndView.setViewName("login");
//        return modelAndView;
//    }
//
//    @PostMapping("/login")
//    public ModelAndView authLoginUser(
//            @RequestParam("email") String email,
//            @RequestParam("password") String password){
//        if (!authService.authenticate(email, password)){
//            return errorModel("Lỗi, đăng nhập thất bại!", "/v1/auth/login", "Quay lại trang đăng nhập");
//        }
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }
//
//    @GetMapping("/signup")
//    public ModelAndView getAuthSignUp(){
//        modelAndView.setViewName("signup");
//        modelAndView.addObject("user", new AuthUserDTO());
//        return modelAndView;
//    }
//
//    @PostMapping("/signup")
//    public ModelAndView authSignUpUser(@ModelAttribute @Valid AuthUserDTO authUserDTO, BindingResult bindingResult){
//        if (bindingResult.hasErrors() || !authUserDTO.getPassword().equals(authUserDTO.getConfirmPassword())){
//            return errorModel("Lỗi, đăng kí thất bại!", "/v1/auth/signup", "Quay lại trang đăng kí");
//        }
//        if (authService.emailExists(authUserDTO.getEmail())){
//            return errorModel("Lỗi, đăng kí thất bại! Email đã tồn tại", "/v1/auth/signup", "Quay lại trang đăng kí");
//        }
//        modelAndView.setViewName("index");
//        User user = modelMapper.map(authUserDTO, User.class);
//        user.setStt(userService.getUsers().size()+1);
//        user.setRole("USER");
//        user.setPhoneNumber("RANDOM_PHONE_NUMBER");
//        user.setAge(18);
//        user.setUsername("RANDOM_USERNAME");
//        userService.addUser(user);
//        return modelAndView;
//    }
}
