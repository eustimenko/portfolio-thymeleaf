package com.eustimenko.portfolio.thymeleaf.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    public void getUsersView() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("users"));
    }

    @Test
    public void getNonEmptyUsersView() throws Exception {
        mvc.perform(get("/new?name=Eugene"));

        mvc.perform(get("/get?name=Eugene"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", is(not(empty()))));
    }

    @Test
    public void createUser() throws Exception {
        mvc.perform(get("/new?name=Eugene"))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attribute("users", is(not(empty()))));
    }

    @Test
    public void pageNotFound() throws Exception {
        mvc.perform(get("/abc"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void errorDueToWork() throws Exception {
        jdbc.setDataSource(null);

        mvc.perform(get("/"))
                .andExpect(status().isNotFound());
    }
}
