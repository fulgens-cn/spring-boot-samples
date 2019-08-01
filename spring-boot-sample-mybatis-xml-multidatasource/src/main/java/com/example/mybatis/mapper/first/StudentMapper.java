package com.example.mybatis.mapper.first;

import com.example.mybatis.entity.first.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {

    Student selectById(Long id);
}
