package com.eustimenko.portfolio.thymeleaf.exception;

public class DaoException extends org.springframework.dao.DataAccessException {
    public DaoException(Throwable cause) {
        super("DAO layer occurs the error", cause);
    }
}
