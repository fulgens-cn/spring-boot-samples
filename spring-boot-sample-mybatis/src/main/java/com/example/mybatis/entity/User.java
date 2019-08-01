package com.example.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /** id */
    private Long id;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
