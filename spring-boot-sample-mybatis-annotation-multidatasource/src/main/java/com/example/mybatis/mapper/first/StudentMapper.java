package com.example.mybatis.mapper.first;

import com.example.mybatis.entity.first.Student;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper {

    @Select("SELECT id, name, age FROM tb_student WHERE id = #{id}")
    Student selectById(Long id);
}
