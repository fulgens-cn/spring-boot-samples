package com.example.jdbc.dao.second;

import com.example.jdbc.entity.second.Teacher;

import java.util.List;

public interface TeacherDao {

    int save(Teacher teacher);

    int deleteById(Long id);

    int update(Teacher teacher);

    Teacher findById(Long id);

    List<Teacher> findAll();
}
