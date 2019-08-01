package com.example.mybatis.mapper;

import com.example.mybatis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey(1L);
        assertEquals("jerry", user.getUsername());
        assertEquals("123456789", user.getPassword());
    }

    @Test
    public void deleteByPrimaryKey() {
        int rows = userMapper.deleteByPrimaryKey(2L);
        assertTrue(rows == 1);
    }

    @Test
    public void insert() {
        User user = new User("jerry", "123456789");
        int rows = userMapper.insert(user);
        log.info("insert into tb_user return id: {}", user.getId());
        assertTrue(rows == 1);
        User user2 = new User("tom", "123456789");
        rows = userMapper.insert(user2);
        log.info("insert into tb_user return id: {}", user2.getId());
        assertTrue(rows == 1);
    }

    @Test
    public void updateByPrimaryKeySelective() {
        int rows = userMapper.updateByPrimaryKeySelective(User.builder().id(1L).username("merry").build());
        assertTrue(rows == 1);
    }

    @Test
    public void updateByPrimaryKey() {
        int rows = userMapper.updateByPrimaryKeySelective(User.builder().id(1L).username("merry").password("123456").build());
        assertTrue(rows == 1);
    }

    @Test
    public void selectByUsername() {
        assertEquals("123456", userMapper.selectByUsername("merry").getPassword());
    }

    @Test
    public void selectByUsernameLike() {
        assertTrue(userMapper.selectByUsernameLike("err").size() > 0);
    }
}