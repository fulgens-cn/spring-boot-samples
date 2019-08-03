package com.example.mybatis.mapper;

import com.example.mybatis.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsertSelective() {
        User user = new User();
        user.setUsername("ccmouse");
        user.setPassword("123456789");
        assertTrue(userMapper.insertSelective(user) == 1);
    }

    @Test
    public void testUpdateByExample() {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", "ccmouse");
        User user = new User();
        user.setPhone("18312345678");
        assertTrue(userMapper.updateByExampleSelective(user, example) > 0);
    }

    @Test
    public void testSelectByExample() {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("gender", "0");
        List<User> users = userMapper.selectByExample(example);
        assertTrue(users.size() > 0);
    }

    @Test
    public void testPageQuery() {
        PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(() -> userMapper.selectAll());
        assertTrue(pageInfo.getList().size() > 0);
        assertTrue(pageInfo.isIsFirstPage());
    }

}