package com.eustimenko.portfolio.thymeleaf.service;

import com.eustimenko.portfolio.thymeleaf.exception.DaoException;
import com.eustimenko.portfolio.thymeleaf.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserService service;

    @Test
    public void addUserSuccessfully() {
        final User expected = new User(1L, "User");
        when(service.addUser("User")).thenReturn(expected);

        final User result = service.addUser("User");

        assertEquals(expected.getName(), result.getName());
    }

    @Test(expected = DaoException.class)
    public void addUserWithError() {
        when(service.addUser("User")).thenThrow(new DaoException(new RuntimeException()));

        service.addUser("User");
    }

    @Test
    public void listSuccessfully() {
        when(service.list()).thenReturn(Collections.emptyList());

        final List<User> result = service.list();

        assertTrue(result.isEmpty());
    }

    @Test(expected = DaoException.class)
    public void listWithError() {
        when(service.list()).thenThrow(new DaoException(new RuntimeException()));

        service.list();
    }

    @Test
    public void listByNameSuccessfully() {
        when(service.list("Eugene")).thenReturn(Collections.singletonList(new User(1L, "Eugene")));

        final List<User> result = service.list("Eugene");

        assertTrue(result.size() == 1);
    }

    @Test(expected = DaoException.class)
    public void listByNameWithError() {
        when(service.list("Eugene")).thenThrow(new DaoException(new RuntimeException()));

        service.list("Eugene");
    }
}
