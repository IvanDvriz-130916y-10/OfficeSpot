package ru.dvornikov.TestSecurity2dbThemeleaf.service;

import ru.dvornikov.TestSecurity2dbThemeleaf.dto.UserDto;
import ru.dvornikov.TestSecurity2dbThemeleaf.entity.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail (String email);

    List<UserDto> findAllUsers ();
}
