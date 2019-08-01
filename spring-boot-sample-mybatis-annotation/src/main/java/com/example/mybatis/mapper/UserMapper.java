package com.example.mybatis.mapper;

import com.example.mybatis.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public interface UserMapper {

    @Select("SELECT id, username, password, create_time, update_time FROM tb_user WHERE id = #{id}")
    @Results(id = "userResult", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
    })
    User selectByPrimaryKey(Long id);

    @Delete("DELETE FROM tb_user WHERE id = #{id}")
    int deleteByPrimaryKey(Long id);

    @Insert("INSERT INTO tb_user(username, password) VALUES(#{username}, #{password})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id", before = false, resultType = Long.class)
    int insert(User user);

    @UpdateProvider(type = UserSqlBuilder.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User user);

    @Update("UPDATE tb_user SET username = #{username}, password = #{password} WHERE id = #{id}")
    int updateByPrimaryKey(User user);

    @Select("SELECT id, username, password, create_time, update_time FROM tb_user WHERE username = #{username}")
    @ResultMap("userResult")
    User selectByUsername(String username);

    @SelectProvider(type = UserSqlBuilder.class, method = "selectByUsernameLike")
    List<User> selectByUsernameLike(String username);

    class UserSqlBuilder {

        public static String updateByPrimaryKeySelective(User user) {
            return new SQL(){{
                UPDATE("tb_user");
                if (user.getUsername() != null) {
                    SET("username = #{username}");
                }
                if (user.getPassword() != null) {
                    SET("password = #{password}");
                }
                WHERE("id = #{id}");
            }}.toString();
        }

        public static String selectByUsernameLike(String username) {
            return new SQL(){{
                SELECT("id", "username", "password", "create_time", "update_time");
                FROM("tb_user");
                if (username != null) {
                    WHERE("username LIKE CONCAT(CONCAT('%', #{username}), '%')");
                }
                ORDER_BY("id");
            }}.toString();
        }

    }
}
