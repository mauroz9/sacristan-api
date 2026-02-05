package com.sacristan.api.global.error;

import com.sacristan.api.global.error.exceptions.BadRequestException;
import com.sacristan.api.global.error.exceptions.JwtTokenException;
import com.sacristan.api.global.error.exceptions.arguments.AlreadyUsedEmailException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.sasl.AuthenticationException;
import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Log
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*
    * CODIGOS DE ERRORES PERSONALIZADOS:
    *   * 001: Email already in use
    * */

    /* -- DATABASE --  */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Data integrity violation: " + ex.getMostSpecificCause().getMessage());
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Data integrity violation");
        pb.setInstance(URI.create(request.getRequestURI()));
        return pb;
    }

    /* -- SECURITY -- */

    // MANEJAR RefreshTokenException
    // RefreshTokenNotFoundException

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Authentication failed");
        pb.setInstance(URI.create(request.getRequestURI()));
        return pb;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Authentication error");
        pb.setInstance(URI.create(request.getRequestURI()));
        return pb;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Access denied");
        pb.setInstance(URI.create(request.getRequestURI()));
        return pb;
    }
    @ExceptionHandler(JwtTokenException.class)
    public ProblemDetail handleJwtTokenException(JwtTokenException ex, HttpServletRequest request) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Invalid JWT token");
        pb.setInstance(URI.create(request.getRequestURI()));
        return pb;
    }

    /* -- GENERIC -- */
    @ExceptionHandler(NoSuchElementException.class)
    public ProblemDetail handleNoSuchElementException(NoSuchElementException ex, HttpServletRequest request) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Resource not found");
        pb.setInstance(URI.create(request.getRequestURI()));
        return pb;
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Bad request");
        pb.setInstance(URI.create(request.getRequestURI()));
        return pb;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Illegal argument/s provided");
        pb.setInstance(URI.create(request.getRequestURI()));

        return pb;
    }

    @ExceptionHandler(AlreadyUsedEmailException.class)
    public ProblemDetail handleAlreadyUsedEmailException(AlreadyUsedEmailException ex, HttpServletRequest request) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, ex.getMessage() + ": " + ex.getEmail()
        );
        pb.setType(URI.create("about:blank"));
        pb.setTitle("Illegal argument/s provided");
        pb.setInstance(URI.create(request.getRequestURI()));

        pb.setProperties(Map.of(
                "error", "001",
                "detail", ex.getEmail()
        ));

        return pb;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleRemainingExceptions(Exception ex, HttpServletRequest request) {

        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());

        pb.setType(URI.create("about:blank"));
        pb.setTitle("Something unexpected happened");
        pb.setInstance(URI.create(request.getRequestURI()));
        pb.setProperty("exception", ex.getClass().getName());

        log.info(ex.getMessage());

        return pb;

    }
}
