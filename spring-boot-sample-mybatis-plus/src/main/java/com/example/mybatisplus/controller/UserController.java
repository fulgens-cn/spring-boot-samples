package com.example.mybatisplus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.entity.User;
import com.example.mybatisplus.service.IUserService;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author fulgens
 * @since 2019-08-04
 */
@RestController
@RequestMapping("/mybatisplus/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public String saveUser(@RequestBody User user) {
        boolean save = userService.save(user);
        return save ? "success" : "error";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id) {
        boolean remove = userService.removeById(id);
        return remove ? "success" : "error";
    }

    @PutMapping("/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        boolean update = userService.updateById(user);
        return update ? "success" : "error";
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping("")
    public IPage<User> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return userService.page(new Page<>(pageNum, pageSize));
    }
}
