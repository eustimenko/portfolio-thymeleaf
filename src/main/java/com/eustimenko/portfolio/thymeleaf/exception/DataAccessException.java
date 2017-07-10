package com.eustimenko.portfolio.thymeleaf.exception;

public class DataAccessException extends org.springframework.dao.DataAccessException {
    public DataAccessException(Throwable cause) {
        super("DAO layer occurs the error", cause);
    }
}
