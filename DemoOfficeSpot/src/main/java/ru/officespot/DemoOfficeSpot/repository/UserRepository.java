package ru.officespot.DemoOfficeSpot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.officespot.DemoOfficeSpot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}