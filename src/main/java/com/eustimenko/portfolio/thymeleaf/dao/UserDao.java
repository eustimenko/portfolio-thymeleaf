package com.eustimenko.portfolio.thymeleaf.dao;

import com.eustimenko.portfolio.thymeleaf.model.User;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserDao {

    void insertUser(String name);

    List<User> findByName(String name) throws DataAccessException;

    User findById(long id) throws DataAccessException;

    List<User> findAll() throws DataAccessException;
}
