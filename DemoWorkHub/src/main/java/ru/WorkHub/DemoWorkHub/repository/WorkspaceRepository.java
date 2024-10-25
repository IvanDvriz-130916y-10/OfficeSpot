package ru.WorkHub.DemoWorkHub.repository; // Изменено название пакета на соответствующее проекту WorkHub

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.WorkHub.DemoWorkHub.entity.Workspace; // Изменено с Student на Workspace

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    // Можно добавить специфические методы для работы с рабочими пространствами, если потребуется
}