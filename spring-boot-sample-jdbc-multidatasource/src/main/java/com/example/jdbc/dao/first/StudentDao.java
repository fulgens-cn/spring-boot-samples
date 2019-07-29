package com.example.jdbc.dao.first;

import com.example.jdbc.entity.first.Student;

import java.util.List;

public interface StudentDao {

    int save(Student student);

    int deleteById(Long id);

    int update(Student student);

    Student findById(Long id);

    List<Student> findAll();
}
