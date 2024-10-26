package ru.officespot.DemoOfficeSpot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.officespot.DemoOfficeSpot.dto.UserDto;
import ru.officespot.DemoOfficeSpot.entity.User;
import ru.officespot.DemoOfficeSpot.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto customer = new UserDto(); // Создаем объект CustomerDto
        model.addAttribute("user", customer); // Добавляем его в модель
        return "register"; // Возвращаем представление для страницы регистрации
    }

    @PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "На этот адрес электронной почты уже зарегистрирована учетная запись.");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/login?success";
    }

    @GetMapping("/list")
    public String getAllUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
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
        return "redirect:/list";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/list";
    }
}