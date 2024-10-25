package ru.officespot.DemoOfficeSpot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.officespot.DemoOfficeSpot.entity.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
}