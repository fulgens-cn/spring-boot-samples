package com.example.jdbc.dao.first.impl;

import com.example.jdbc.dao.first.StudentDao;
import com.example.jdbc.entity.first.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentDaoImplTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void save() {
        Student student = Student.builder().name("tom").age(18).build();
        assertTrue(studentDao.save(student) > 0);
        assertTrue(studentDao.save(Student.builder().name("jerry").age(18).build()) > 0);
    }

    @Test
    public void deleteById() {
        assertTrue(studentDao.deleteById(2L) > 0);
    }

    @Test
    public void update() {
        Student student = Student.builder().id(1L).name("tom").age(20).build();
        assertTrue(studentDao.update(student) > 0);
        assertTrue(studentDao.findById(1L).getAge() == 20);
    }

    @Test
    public void findById() {
        assertEquals("tom", studentDao.findById(1L).getName());
    }

    @Test
    public void findAll() {
        assertTrue(studentDao.findAll().size() == 1);
    }
}