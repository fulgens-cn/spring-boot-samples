package com.example.jdbc.dao.second.impl;

import com.example.jdbc.dao.second.TeacherDao;
import com.example.jdbc.entity.second.Teacher;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    private final JdbcTemplate secondJdbcTemplate;

    public TeacherDaoImpl(JdbcTemplate secondJdbcTemplate) {
        this.secondJdbcTemplate = secondJdbcTemplate;
    }

    @Override
    public int save(Teacher student) {
        String sql = "INSERT INTO tb_teacher(name, age) VALUES(?, ?)";
        return secondJdbcTemplate.update(sql, student.getName(), student.getAge());
    }

    @Override
    public int deleteById(Long id) {
        String sql = "DELETE FROM tb_teacher WHERE id = ?";
        return secondJdbcTemplate.update(sql, id);
    }

    @Override
    public int update(Teacher student) {
        String sql = "UPDATE tb_teacher SET name = ?, age = ? WHERE id = ?";
        return secondJdbcTemplate.update(sql, student.getName(), student.getAge(), student.getId());
    }

    @Override
    public Teacher findById(Long id) {
        String sql = "SELECT id, name, age FROM tb_teacher WHERE id = ?";
        return secondJdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Teacher.class), id);
    }

    @Override
    public List<Teacher> findAll() {
        String sql = "SELECT id, name, age FROM tb_teacher";
        return secondJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Teacher.class));
    }
}
