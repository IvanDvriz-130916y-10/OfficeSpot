package ru.officespot.DemoOfficeSpot.dto;

import lombok.Data;
import ru.officespot.DemoOfficeSpot.entity.User;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // Метод для преобразования сущности User в DTO
    public static UserDto fromEntity(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());

        // Разделение имени пользователя на firstName и lastName
        String[] nameParts = user.getName().split(" ");
        dto.setFirstName(nameParts[0]);
        dto.setLastName(nameParts.length > 1 ? nameParts[1] : "");

        dto.setEmail(user.getEmail());
        // Не устанавливаем пароль в DTO для безопасности
        return dto;
    }
}