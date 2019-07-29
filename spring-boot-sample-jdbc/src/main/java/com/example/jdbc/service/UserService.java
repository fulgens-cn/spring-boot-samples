package com.example.jdbc.service;

import com.example.jdbc.common.utils.PageBean;
import com.example.jdbc.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    void batchSave(List<User> users);

    void delete(Long id);

    User update(User user);

    User findById(Long id);

    List<User> findByIdIn(Long... ids);

    PageBean<User> pageQuery(Integer page, Integer pageSize);
}
