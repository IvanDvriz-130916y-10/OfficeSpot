package ru.WorkHub.DemoWorkHub.dto; // Изменено название пакета на соответствующее проекту WorkHub

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto { // Изменено с UserDto на CustomerDto
    private Long id;

    @NotEmpty(message = "First name should not be empty") // Добавлено сообщение об ошибке
    private String firstName;

    @NotEmpty(message = "Last name should not be empty") // Добавлено сообщение об ошибке
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid") // Добавлено сообщение об ошибке для Email
    private String email;

    @NotEmpty(message = "Password should not be empty") // Исправлено сообщение об ошибке
    private String password;
}