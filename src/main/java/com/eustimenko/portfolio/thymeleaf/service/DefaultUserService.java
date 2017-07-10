package com.eustimenko.portfolio.thymeleaf.service;

import com.eustimenko.portfolio.thymeleaf.dao.UserDao;
import com.eustimenko.portfolio.thymeleaf.exception.DataAccessException;
import com.eustimenko.portfolio.thymeleaf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserService implements UserService {

    private final UserDao dao;

    @Autowired
    public DefaultUserService(UserDao dao) {
        this.dao = dao;
    }

    public User addUser(String name) {
        try {
            final long insertedUserId = dao.insertUser(name);

            return dao.findById(insertedUserId);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public List<User> list() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public List<User> list(String name) {
        try {
            return dao.findByName(name);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
