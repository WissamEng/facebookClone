package com.fsd09.programming3.finalproject.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;

/**
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String message = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse();
        if (message.contains("final_project_user.user_name")){
            errorResponse.setMessage("username is duplicated");
        }
        if (message.contains("final_project_user.email")){
            errorResponse.setMessage("email is duplicated");
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        StringBuilder errorMessage = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error->{
            String field = ((FieldError) error).getField();
            String defaultMessage = error.getDefaultMessage();
            errorMessage.append(field).append(" : ").append(defaultMessage).append("<br>");
        });
        errorResponse.setMessage(errorMessage.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse("something went wrong");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserPrincipalNotFoundException.class)
    public void handleUserPrincipalNotFoundException(Exception e, HttpServletResponse response) throws IOException {
        response.sendRedirect("/login_page?error=true");
    }
}
