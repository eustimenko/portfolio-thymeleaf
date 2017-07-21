package com.eustimenko.portfolio.thymeleaf.service;

import com.eustimenko.portfolio.thymeleaf.dao.UserDao;
import com.eustimenko.portfolio.thymeleaf.exception.DaoException;
import com.eustimenko.portfolio.thymeleaf.model.User;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DefaultUserService implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserDao dao;

    @Autowired
    public DefaultUserService(UserDao dao) {
        this.dao = dao;
    }

    public void addUser(String name) {
        try {
            insertAndGetUser(name);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    private void insertAndGetUser(String name) {
        dao.insertUser(name);
        logger.info("User '{}' is inserted", name);
    }

    public List<User> list() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    public List<User> list(String name) {
        try {
            return dao.findByName(name);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
