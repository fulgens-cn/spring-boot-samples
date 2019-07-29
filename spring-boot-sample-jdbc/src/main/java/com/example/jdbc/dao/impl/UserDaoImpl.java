package com.example.jdbc.dao.impl;

import com.example.jdbc.dao.UserDao;
import com.example.jdbc.entity.User;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_SQL =
            "INSERT INTO tb_user(username, password, nickname, avatar_url, gender, birth_date, phone, country, city) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE tb_user SET username = ?, password = ?, nickname = ?, avatar_url = ?, gender = ?, birth_date = ?, phone = ?, country = ?, city = ? WHERE id = ?";

    private static final String SELECT_SQL_PREFIX =
            "SELECT id, username, password, nickname, avatar_url as avatarUrl, gender, birth_date as birthDate, phone, country, city, create_time as createTime, update_time as updateTime FROM tb_user";

    @Override
    public int save(User user) {
        /*return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getNickname(), user.getAvatarUrl(),
                user.getGender(), user.getBirthDate(), user.getPhone(), user.getPhone(), user.getCountry(), user.getCity());*/
        return jdbcTemplate.update(INSERT_SQL, preparedStatement -> {
            preparedStatement.setString(0, user.getUsername());
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.setString(3, user.getAvatarUrl());
            preparedStatement.setInt(4, user.getGender());
            preparedStatement.setDate(5, new Date(user.getBirthDate().getTime()));
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getCountry());
            preparedStatement.setString(8, user.getCity());
        });
    }

    @Override
    public Long saveAndReturnPrimaryKey(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, new String[]{"id"});
            setPreparedStatement(preparedStatement, user);
            return preparedStatement;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public int[] batchSave(List<User> users) {
        return jdbcTemplate.batchUpdate(INSERT_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                User user = users.get(i);
                setPreparedStatement(preparedStatement, user);
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }
        });
    }

    private void setPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getNickname());
        preparedStatement.setString(4, user.getAvatarUrl());
        preparedStatement.setInt(5, user.getGender());
        preparedStatement.setDate(6, new Date(user.getBirthDate().getTime()));
        preparedStatement.setString(7, user.getPhone());
        preparedStatement.setString(8, user.getCountry());
        preparedStatement.setString(9, user.getCity());
    }

    @Override
    public int deleteById(Long id) {
        String sql = "DELETE FROM tb_user WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update(UPDATE_SQL, user.getUsername(), user.getPassword(), user.getNickname(),
                user.getAvatarUrl(), user.getGender(), user.getBirthDate(), user.getPhone(), user.getCountry(),
                user.getCity(), user.getId());
    }

    @Override
    public int[] batchUpdate(List<User> users) {
        return jdbcTemplate.batchUpdate(UPDATE_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                User user = users.get(i);
                setPreparedStatement(preparedStatement, user);
                preparedStatement.setLong(10, user.getId());
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }
        });
    }

    @Override
    public User findById(Long id) {
        String sql = SELECT_SQL_PREFIX + " WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public List<User> findByIdIn(Long[] ids) {
        String sql = SELECT_SQL_PREFIX + " WHERE id IN (?)";
        String param = StringUtils.collectionToCommaDelimitedString(Arrays.asList(ids).stream().map(id -> "'" + id + "'").collect(Collectors.toList()));
        return jdbcTemplate.queryForList(sql, User.class, param);
    }

    @Override
    public User findByUsername(String username) {
        String sql = SELECT_SQL_PREFIX + " WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
    }

    @Override
    public Integer count() {
        String sql = "SELECT count(1) FROM tb_user";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SELECT_SQL_PREFIX, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findList(Integer page, Integer pageSize) {
        String sql = SELECT_SQL_PREFIX + " LIMIT ?, ?";
        Integer offset = (page - 1) * pageSize;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), offset, pageSize);
    }

}
