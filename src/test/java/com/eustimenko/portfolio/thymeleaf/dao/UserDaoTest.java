package com.eustimenko.portfolio.thymeleaf.dao;

import com.eustimenko.portfolio.thymeleaf.model.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao dao;

    @Autowired
    private JdbcTemplate jdbc;

    @After
    public void tearDown() {
        jdbc.execute("DELETE FROM users");
    }

    @Test
    public void findAll() {
        for (int i = 0; i < 10; i++) {
            dao.insertUser("User_" + i);
        }

        final List<User> result = dao.findAll();

        assertFalse(result.isEmpty());
        assertTrue(result.size() >= 10);
    }

    @Test
    public void findAllEmpty() {
        final List<User> result = dao.findAll();

        assertTrue(result.isEmpty());
    }

    @Test
    public void insertUser() {
        final int preInsertSize = dao.findAll().size();

        final String newUserName = "User inserted from test";
        dao.insertUser(newUserName);

        final int postInsertSize = dao.findAll().size();
        assertTrue((postInsertSize - preInsertSize) == 1);
    }

    @Test
    public void findByIdSuccessfully() {
        final Long newUserId = 1L;
        final String newUserName = "User to find by id";

        jdbc.update("INSERT INTO users(id, name) VALUES (?, ?)", newUserId, newUserName);

        final User result = dao.findById(newUserId);

        assertNotNull(result);
        assertEquals(newUserName, result.getName());
    }

    @Test(expected = DataAccessException.class)
    public void findByIdWithError() {
        Long newUserId = 1L;
        final String newUserName = "User to find by id with error";

        jdbc.update("INSERT INTO users(id, name) VALUES (?, ?)", newUserId, newUserName);

        dao.findById(++newUserId);
    }

    @Test
    public void findByNameSuccessfully() {
        dao.insertUser("First user");
        dao.insertUser("Second User");

        final List<User> result = dao.findByName("UsEr");

        assertTrue(result.size() == 2);
    }

    @Test
    public void findByNameEmpty() {
        final String newUserName = "User inserted from test";
        dao.insertUser(newUserName);

        final List<User> result = dao.findByName("notFound");

        assertTrue(result.isEmpty());
    }
}
