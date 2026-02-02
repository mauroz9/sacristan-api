package com.sacristan.api.error;

import com.sacristan.api.error.exceptions.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Data integrity violation: " + ex.getMostSpecificCause().getMessage());
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Data integrity violation");
        pb.setInstance(URI.create("about:blank"));
        return pb;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ProblemDetail handleNoSuchElementException(NoSuchElementException ex) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Resource not found");
        pb.setInstance(URI.create("about:blank"));
        return pb;
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException(BadRequestException ex) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Bad request");
        pb.setInstance(URI.create("about:blank"));
        return pb;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Illegal argument/s provided");
        pb.setInstance(URI.create("about:blank"));
        return pb;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleRemainingExceptions(Exception ex) {

        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        pb.setType(URI.create("about:blank"));
        pb.setTitle("Something unexpected happened");
        pb.setInstance(URI.create("about:blank"));
        pb.setProperty("exception", ex.getClass().getName());

        return pb;

    }

}
