package com.example.jdbc.dao;

import com.example.jdbc.entity.User;

import java.util.List;

public interface UserDao {

    /**
     * 保存用户信息
     *
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 保存用户信息并返回主键
     *
     * @param user
     * @return
     */
    Long saveAndReturnPrimaryKey(User user);

    /**
     * 批量保存用户信息
     *
     * @param users
     * @return
     */
    int[] batchSave(List<User> users);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 批量更新用户信息
     *
     * @param users
     * @return
     */
    int[] batchUpdate(List<User> users);

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 根据id批量查找用户
     *
     * @param ids
     * @return
     */
    List<User> findByIdIn(Long[] ids);

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 查询总记录数
     *
     * @return
     */
    Integer count();

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findAll();

    /**
     * 分页查询所有用户
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<User> findList(Integer page, Integer pageSize);
}
