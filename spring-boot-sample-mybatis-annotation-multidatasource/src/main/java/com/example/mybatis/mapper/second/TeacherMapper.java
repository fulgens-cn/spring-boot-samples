package com.example.mybatis.mapper.second;

import com.example.mybatis.entity.second.Teacher;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMapper {

    @Select("SELECT id, name, age FROM tb_teacher WHERE id = #{id}")
    Teacher selectById(Long id);
}
