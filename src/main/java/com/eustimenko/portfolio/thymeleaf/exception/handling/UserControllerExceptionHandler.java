package com.eustimenko.portfolio.thymeleaf.exception.handling;

import com.eustimenko.portfolio.thymeleaf.exception.DaoException;
import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class UserControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerExceptionHandler.class);

    @ExceptionHandler(DaoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleDaoException(DaoException e) {
        logger.error("{}", e.getMessage());
        return "error";
    }
}
