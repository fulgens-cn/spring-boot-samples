package com.example.jdbc.service.impl;

import com.example.jdbc.common.utils.PageBean;
import com.example.jdbc.common.utils.SpringUtils;
import com.example.jdbc.dao.UserDao;
import com.example.jdbc.entity.User;
import com.example.jdbc.exception.ResourceNotFoundException;
import com.example.jdbc.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User save(User user) {
        Long id = userDao.saveAndReturnPrimaryKey(user);
        return userDao.findById(id);
    }

    @Override
    public void batchSave(List<User> users) {
        userDao.batchSave(users);
    }

    @Override
    public void delete(Long id) {
        Optional.ofNullable(userDao.findById(id)).orElseThrow(() -> new ResourceNotFoundException("用户不存在"));
        userDao.deleteById(id);
    }

    @Override
    public User update(User user) {
        User dbUser = userDao.findById(user.getId());
        Optional.ofNullable(dbUser).orElseThrow(() -> new ResourceNotFoundException("用户不存在"));
        SpringUtils.copyPropertiesIgnoreNull(user, dbUser);
        userDao.update(dbUser);
        return dbUser;
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findByIdIn(Long... ids) {
        return userDao.findByIdIn(ids);
    }

    @Override
    public PageBean<User> pageQuery(Integer page, Integer pageSize) {
        Integer totalCount = userDao.count();
        List<User> userList = userDao.findList(page, pageSize);
        return PageBean.newInstance(page, pageSize, totalCount, userList);
    }

}
