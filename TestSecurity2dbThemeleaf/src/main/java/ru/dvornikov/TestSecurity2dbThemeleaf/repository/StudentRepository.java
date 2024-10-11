package ru.dvornikov.TestSecurity2dbThemeleaf.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dvornikov.TestSecurity2dbThemeleaf.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {
}
