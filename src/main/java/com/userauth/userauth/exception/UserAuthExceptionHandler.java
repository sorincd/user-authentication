package com.userauth.userauth.exception;

import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static com.userauth.userauth.exception.ErrorFactory.error;
import static com.userauth.userauth.exception.UserAuthException.userAuthException;
import static java.lang.String.format;
import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class UserAuthExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String REST_CALL_ERROR_MESSAGE = "REST call threw exception [%s] , request=%s";

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(FORBIDDEN)
    @ResponseBody
    public Error exceptionHandler(AccessDeniedException exception, HttpServletRequest request) {
        UserAuthException userauthException = userAuthException(exception);
        logError(userauthException, request);
        return error(userauthException);
    }

    @Order(LOWEST_PRECEDENCE)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error internalErrorHandler(Exception exception, HttpServletRequest request) {
        UserAuthException userauthException = userAuthException(exception);
        logError(userauthException, request);
        return error(userauthException);
    }

    private void logError(UserAuthException e, HttpServletRequest request) {
        logger.error(format(REST_CALL_ERROR_MESSAGE, e.getId(), fullURL(request)), e);
    }

    private static String fullURL(HttpServletRequest request) {
        return request.getMethod() + " " + request.getRequestURL() +
                ((request.getQueryString() != null) ? "?" + request.getQueryString() : "");
    }
}
