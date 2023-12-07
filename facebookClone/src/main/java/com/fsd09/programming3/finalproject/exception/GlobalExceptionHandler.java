package com.fsd09.programming3.finalproject.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        System.out.println("===========================>"+e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        if (message.contains("final_project_user.user_name")){
            errorResponse.setMessage("username is duplicated");
        }
        if (message.contains("final_project_user.email")){
            errorResponse.setMessage("email is duplicated");
        }
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
