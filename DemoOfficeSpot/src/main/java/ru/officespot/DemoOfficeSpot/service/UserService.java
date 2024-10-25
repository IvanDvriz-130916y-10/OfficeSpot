package ru.officespot.DemoOfficeSpot.service;

import ru.officespot.DemoOfficeSpot.entity.User;
import ru.officespot.DemoOfficeSpot.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAllUsers();
    void saveUser(UserDto userDto);
    Optional<User> findUserById(Long id);
    User findUserByEmail(String email);
    void deleteUserById(Long id);
}