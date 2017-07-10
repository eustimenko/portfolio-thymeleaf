package com.eustimenko.portfolio.thymeleaf.dao;

import com.eustimenko.portfolio.thymeleaf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@Transactional
public class DefaultUserDao implements UserDao {

    private final JdbcTemplate jdbc;

    @Autowired
    public DefaultUserDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Long insertUser(String name) {
        final SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);

        final Number key = insert.executeAndReturnKey(new MapSqlParameterSource(
                parameters));

        return key.longValue();
    }

    public List<User> findByName(String name) throws DataAccessException {
        String sql = "SELECT id, name FROM users";
        if (!name.isEmpty()) {
            sql += " WHERE LOWER(name) LIKE LOWER(?) ORDER BY name";

            return jdbc.query(sql, getRowMapper(), "%" + name + "%");
        } else {
            sql += " ORDER BY id";
            return jdbc.query(sql, getRowMapper());
        }
    }

    private RowMapper<User> getRowMapper() {
        return (rs, rowNum) -> new User(rs.getLong("id"), rs.getString("name"));
    }

    public User findById(long id) throws DataAccessException {
        return jdbc.queryForObject("SELECT id, name FROM users WHERE id=?", getRowMapper(), id);
    }

    public List<User> findAll() throws DataAccessException {
        return findByName("");
    }
}
