package com.eustimenko.portfolio.thymeleaf.service;

import com.eustimenko.portfolio.thymeleaf.model.User;

import java.util.List;

public interface UserService {

    User addUser(String name);

    List<User> list();

    List<User> list(String name);
}
