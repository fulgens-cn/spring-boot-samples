package com.example.jdbc.web.api;

import com.example.jdbc.common.ServerResponse;
import com.example.jdbc.common.utils.PageBean;
import com.example.jdbc.entity.User;
import com.example.jdbc.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ServerResponse<User> saveUser(@RequestBody User user) {
        return ServerResponse.success(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public ServerResponse deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ServerResponse.success();
    }

    @PutMapping("/{id}")
    public ServerResponse<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return ServerResponse.success(userService.update(user));
    }

    @GetMapping("/{id}")
    public ServerResponse<User> findById(@PathVariable Long id) {
        return ServerResponse.success(userService.findById(id));
    }

    @GetMapping("")
    public ServerResponse<PageBean<User>> pageQuery(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return ServerResponse.success(userService.pageQuery(page, pageSize));
    }
}
