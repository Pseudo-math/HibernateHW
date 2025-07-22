package org.example.dao;

import org.example.model.Student;

import java.util.List;

public interface StudentDao {
    void createStudent(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudent();
    void updateStudent(Student student);
    void deleteStudent(Student student);
}
