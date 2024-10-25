package ru.WorkHub.DemoWorkHub.controller; // Используем соответствующее название пакета

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.WorkHub.DemoWorkHub.dto.CustomerDto; // Используем CustomerDto
import ru.WorkHub.DemoWorkHub.entity.Customer; // Используем сущность Customer
import ru.WorkHub.DemoWorkHub.service.CustomerService; // Используем сервис CustomerService

import java.util.List;

@Controller
public class SecurityController {
    private final CustomerService customerService; // Используем final для неизменяемого поля

    public SecurityController(CustomerService customerService) { // Конструктор для внедрения зависимости
        this.customerService = customerService;
    }

    @GetMapping("/index")
    public String home() {
        return "index"; // Возвращаем представление для главной страницы
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Возвращаем представление для страницы логина
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        CustomerDto customer = new CustomerDto(); // Создаем объект CustomerDto
        model.addAttribute("customer", customer); // Добавляем его в модель
        return "register"; // Возвращаем представление для страницы регистрации
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("customer") CustomerDto customerDto,
                               BindingResult result,
                               Model model) {
        Customer existingCustomer = customerService.findCustomerByEmail(customerDto.getEmail());

        if (existingCustomer != null && existingCustomer.getEmail() != null && !existingCustomer.getEmail().isEmpty()) {
            result.rejectValue("email", null, "На этот адрес электронной почты уже зарегистрирована учетная запись.");
        }

        if (result.hasErrors()) {
            model.addAttribute("customer", customerDto); // Если есть ошибки, возвращаем на форму регистрации
            return "register";
        }

        customerService.saveCustomer(customerDto); // Сохраняем нового клиента
        return "redirect:/register?success"; // Перенаправляем на страницу успеха
    }

    @GetMapping("/customers")
    public String customers(Model model) {
        List<CustomerDto> customers = customerService.findAllCustomers(); // Получаем список всех клиентов
        model.addAttribute("customers", customers); // Добавляем список в модель
        return "customers"; // Возвращаем представление для списка клиентов
    }
}