package ru.officespot.DemoOfficeSpot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.officespot.DemoOfficeSpot.service.UserService;
import ru.officespot.DemoOfficeSpot.dto.UserDto;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "list-users";
    }

    @GetMapping("/addUserForm")
    public String addUserForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "add-user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/users";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/users";
    }
}