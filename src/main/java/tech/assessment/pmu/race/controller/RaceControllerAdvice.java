package tech.assessment.pmu.race.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RaceControllerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(RaceControllerAdvice.class);

    private static final String ERROR_STRING_FORMATTER = "%s : %s";
    private static final String ERROR_LOG_STRING_FORMATTER = "From {}";

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public @ResponseBody String handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest req) {
        LOG.error(ERROR_LOG_STRING_FORMATTER, req.getRequestURI(), ex);
        return String.format(ERROR_STRING_FORMATTER ,HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public @ResponseBody String handleConstraintViolationException(ValidationException ex, HttpServletRequest req) {
        LOG.error(ERROR_LOG_STRING_FORMATTER, req.getRequestURI(), ex);
        return String.format(ERROR_STRING_FORMATTER ,HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody String handleException(Exception ex, HttpServletRequest req) {
        LOG.error(ERROR_LOG_STRING_FORMATTER, req.getRequestURI(), ex);
        return String.format(ERROR_STRING_FORMATTER ,HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
    }
}
