package com.example.mybatis.mapper;

import com.example.mybatis.entity.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
}