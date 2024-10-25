package ru.WorkHub.DemoWorkHub.repository; // Изменено название пакета на соответствующее проекту WorkHub

import org.springframework.data.jpa.repository.JpaRepository;
import ru.WorkHub.DemoWorkHub.entity.Role; // Изменено с TestSecurity2dbThemeleaf на WorkHub.DemoWorkHub

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}