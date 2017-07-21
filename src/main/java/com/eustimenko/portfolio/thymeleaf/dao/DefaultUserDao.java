package com.eustimenko.portfolio.thymeleaf.dao;

import com.eustimenko.portfolio.thymeleaf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class DefaultUserDao implements UserDao {

    private final JdbcTemplate jdbc;
    private RowMapper<User> mapper;

    @Autowired
    public DefaultUserDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @PostConstruct
    public void initNonChangedFields() {
        mapper = new BeanPropertyRowMapper<>(User.class);
    }

    public Long insertUser(String name) {
        jdbc.update(UserQueries.INSERT_USER, name);
        return jdbc.queryForObject(UserQueries.SELECT_LAST_INSERT_ID, Long.class);
    }

    public List<User> findByName(String name) throws DataAccessException {
        return name.isEmpty()
                ? jdbc.query(UserQueries.SELECT_USERS_ORDERED_BY_ID, mapper)
                : jdbc.query(UserQueries.SELECT_USERS_BY_NAME_ORDERED_BY_NAME, mapper, "%" + name + "%");
    }

    public User findById(long id) throws DataAccessException {
        return jdbc.queryForObject(UserQueries.SELECT_USERS_BY_ID, mapper, id);
    }

    public List<User> findAll() throws DataAccessException {
        return findByName("");
    }

    private interface UserQueries {
        String SELECT_USERS_BY_ID = "SELECT id, name FROM users WHERE id=?";
        String SELECT_USERS_ORDERED_BY_ID = "SELECT id, name FROM users ORDER BY id";
        String SELECT_USERS_BY_NAME_ORDERED_BY_NAME = "SELECT id, name FROM users WHERE name LIKE ? ORDER BY name";
        String INSERT_USER = "INSERT INTO users(name) VALUES(?)";
        String SELECT_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";
    }
}
