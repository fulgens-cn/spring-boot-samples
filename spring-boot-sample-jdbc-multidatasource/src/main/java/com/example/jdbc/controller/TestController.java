package com.example.jdbc.controller;

import com.example.jdbc.dao.first.StudentDao;
import com.example.jdbc.dao.second.TeacherDao;
import com.example.jdbc.entity.first.Student;
import com.example.jdbc.entity.second.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class TestController {

    private final StudentDao studentDao;

    private final TeacherDao teacherDao;

    public TestController(StudentDao studentDao, TeacherDao teacherDao) {
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
    }

    @RequestMapping("/list")
    @ResponseBody
    public String list() {
        List<Student> studentList = studentDao.findAll();
        List<Teacher> teacherList = teacherDao.findAll();
        log.info("studentList: {}", studentList);
        log.info("teacherList: {}", teacherList);
        return "ok";
    }
}
