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

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

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