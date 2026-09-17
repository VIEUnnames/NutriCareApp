package com.nutricare.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(UnauthorizedActionException.class)
    public ResponseEntity<ProblemDetailResponse> handleUnauthorizedActionException(UnauthorizedActionException ex, WebRequest request) {
        ProblemDetailResponse error = new ProblemDetailResponse(
                "Unauthorized",
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ProblemDetailResponse> handleInvalidCredentialsException(InvalidCredentialsException  ex, WebRequest request){
        ProblemDetailResponse error =  new ProblemDetailResponse(
                "Unauthorized",
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidUserInputException.class)
    public ResponseEntity<ProblemDetailResponse> handleInvalidUserInputException(InvalidUserInputException ex, WebRequest request){
        ProblemDetailResponse error = new ProblemDetailResponse(
                "Not Acceptable",
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemDetailResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request){
        ProblemDetailResponse error = new ProblemDetailResponse(
                "Type Mismatch",
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ProblemDetailResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request)
    {
        ProblemDetailResponse error = new ProblemDetailResponse(
                "User not found",
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetailResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request)
    {
        ProblemDetailResponse error = new ProblemDetailResponse(
                "Resource not found",
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ProblemDetailResponse> handleDuplicateResourceException(DuplicateResourceException ex, WebRequest request)
    {
        ProblemDetailResponse error = new ProblemDetailResponse(
                "Duplicate resources",
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return  new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetailResponse> handleGlobalException(Exception ex, WebRequest request) {
        ProblemDetailResponse error = new ProblemDetailResponse(
                "Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Đã xảy ra lỗi hệ thống: " + ex.getMessage(),
                request.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
