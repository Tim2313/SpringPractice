package com.example.repository;

import com.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    void deleteByEmail(String email);
    Student findByEmail(String email);

}
