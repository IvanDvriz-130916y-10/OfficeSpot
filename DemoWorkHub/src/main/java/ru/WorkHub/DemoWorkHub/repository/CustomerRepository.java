package ru.WorkHub.DemoWorkHub.repository; // Изменено название пакета на соответствующее проекту WorkHub

import org.springframework.data.jpa.repository.JpaRepository;
import ru.WorkHub.DemoWorkHub.entity.Customer; // Изменено с User на Customer

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email); // Изменено с User на Customer
}