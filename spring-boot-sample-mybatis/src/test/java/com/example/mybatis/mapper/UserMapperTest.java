package com.example.mybatis.mapper;

import com.example.mybatis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByPrimaryKey() {
        assertEquals(userMapper.selectByPrimaryKey(1L).getUsername(), "jerry");
    }

    @Test
    public void deleteByPrimaryKey() {
        assertTrue(userMapper.deleteByPrimaryKey(2L) > 0);
    }

    @Test
    public void insert() {
        User user = new User("tom", "123456");
        assertTrue(userMapper.insert(user) > 0);
    }

    @Test
    public void insertSelective() {
        User user = new User("merry", "123456");
        assertTrue(userMapper.insertSelective(user) > 0);
    }

    @Test
    public void updateByPrimaryKeySelective() {
        User user = User.builder().id(1L).username("jerry").build();
        assertTrue(userMapper.updateByPrimaryKeySelective(user) > 0);
    }

    @Test
    public void updateByPrimaryKey() {
        User user = User.builder().id(1L).username("jerry").password("123456789").build();
        assertTrue(userMapper.updateByPrimaryKey(user) > 0);
    }

    @Test
    public void selectByUsername() {
        assertEquals(userMapper.selectByUsername("jerry").getPassword(), "123456789");
    }
}