package ru.dvornikov.TestSecurity2dbThemeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dvornikov.TestSecurity2dbThemeleaf.entity.User;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByEmail (String email);
}
