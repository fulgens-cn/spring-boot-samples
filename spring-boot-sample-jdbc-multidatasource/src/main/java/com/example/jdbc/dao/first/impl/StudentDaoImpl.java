package com.example.jdbc.dao.first.impl;

import com.example.jdbc.dao.first.StudentDao;
import com.example.jdbc.entity.first.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    private final JdbcTemplate firstJdbcTemplate;

    public StudentDaoImpl(JdbcTemplate firstJdbcTemplate) {
        this.firstJdbcTemplate = firstJdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "INSERT INTO tb_student(name, age) VALUES(?, ?)";
        return firstJdbcTemplate.update(sql, student.getName(), student.getAge());
    }

    @Override
    public int deleteById(Long id) {
        String sql = "DELETE FROM tb_student WHERE id = ?";
        return firstJdbcTemplate.update(sql, id);
    }

    @Override
    public int update(Student student) {
        String sql = "UPDATE tb_student SET name = ?, age = ? WHERE id = ?";
        return firstJdbcTemplate.update(sql, student.getName(), student.getAge(), student.getId());
    }

    @Override
    public Student findById(Long id) {
        String sql = "SELECT id, name, age FROM tb_student WHERE id = ?";
        return firstJdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), id);
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT id, name, age FROM tb_student";
        return firstJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
    }
}
