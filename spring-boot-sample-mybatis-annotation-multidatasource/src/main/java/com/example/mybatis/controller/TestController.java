package com.example.mybatis.controller;

import com.example.mybatis.entity.first.Student;
import com.example.mybatis.entity.second.Teacher;
import com.example.mybatis.mapper.first.StudentMapper;
import com.example.mybatis.mapper.second.TeacherMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final StudentMapper studentMapper;

    private final TeacherMapper teacherMapper;

    public TestController(StudentMapper studentMapper, TeacherMapper teacherMapper) {
        this.studentMapper = studentMapper;
        this.teacherMapper = teacherMapper;
    }

    @GetMapping("/students/{id}")
    public Student selectById(@PathVariable Long id) {
        return studentMapper.selectById(id);
    }

    @GetMapping("/teachers/{id}")
    public Teacher findById(@PathVariable Long id) {
        return teacherMapper.selectById(id);
    }
}
