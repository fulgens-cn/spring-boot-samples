package com.example.mybatis.mapper;

import com.example.mybatis.entity.User;

public interface UserMapper {

    User selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);

    int insert(User user);

    int insertSelective(User user);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);

    User selectByUsername(String username);

}
