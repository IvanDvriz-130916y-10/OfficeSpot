package ru.dvornikov.TestSecurity2dbThemeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dvornikov.TestSecurity2dbThemeleaf.entity.Role;

public interface RoleRepository extends JpaRepository <Role, Long> {

    Role findByName (String name);
}
