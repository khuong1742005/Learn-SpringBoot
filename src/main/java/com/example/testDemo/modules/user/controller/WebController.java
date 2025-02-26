package com.example.testDemo.modules.user.controller;

import jakarta.validation.Valid;
import com.example.testDemo.modules.user.dtos.UserDTO;
import com.example.testDemo.modules.user.entities.User;
import com.example.testDemo.modules.user.services.impl.IUserServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class WebController {
    private final ModelAndView modelAndView;
    private final ModelMapper modelMapper;
    private final IUserServiceImpl userService;
    private ArrayList<UserDTO> usersDTO = new ArrayList<>();

    public WebController(ModelAndView modelAndView, ModelMapper modelMapper, IUserServiceImpl userService) {
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
        usersDTO = modelMapper.map(userService.getUsers(), new TypeToken<List<UserDTO>>(){}.getType());
        modelAndView.addObject("users", usersDTO);
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
        modelAndView.addObject("message", "Nhập id của user muốn xóa");
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
}
