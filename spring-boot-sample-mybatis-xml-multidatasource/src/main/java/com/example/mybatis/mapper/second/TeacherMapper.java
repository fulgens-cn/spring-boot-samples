package com.example.mybatis.mapper.second;

import com.example.mybatis.entity.second.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMapper {

    Teacher selectById(Long id);
}
