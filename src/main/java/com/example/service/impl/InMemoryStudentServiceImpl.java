package com.example.service.impl;

import com.example.model.Student;
import com.example.repository.InMemoryStudentDAO;
import com.example.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // анотация которая говорит спрингу что это сервис
@AllArgsConstructor //анотация которая позволяет пропустить создание окнструктора
public class InMemoryStudentServiceImpl implements StudentService {

    private final InMemoryStudentDAO studentRepository;

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAllStudents();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.saveStudent(student);
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.updateStudent(student);
    }

    @Override
    public void deleteStudent(String email) {
        studentRepository.deleteStudent(email);
    }
}
