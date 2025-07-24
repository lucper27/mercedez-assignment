package com.swapi.adapter.infrastructure.rest.exception;

import com.swapi.adapter.application.exception.PeopleClientException;
import com.swapi.adapter.application.exception.PlanetClientException;
import com.swapi.adapter.application.exception.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.validation.BindException;


import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> handleArgumentMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String message = String.format("Invalid value '%s' for parameter '%s'. Expected type is '%s'.",
                ex.getValue(), ex.getName(), ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown");
        return buildErrorResponse(HttpStatus.BAD_REQUEST, message, request.getRequestURI());
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class, BindException.class })
    public ResponseEntity<ErrorResponseDTO> handleBindingExceptions(Exception ex, HttpServletRequest request) {
        String message = "Invalid request parameters. Check types and format.";
        return buildErrorResponse(HttpStatus.BAD_REQUEST, message, request.getRequestURI());
    }


    @ExceptionHandler(PeopleClientException.class)
    public ResponseEntity<ErrorResponseDTO> handlePeopleClientException(PeopleClientException ex, HttpServletRequest request) {
        String message = "Error occurred while fetching people data.";
        return buildErrorResponse(HttpStatus.BAD_GATEWAY, message, request.getRequestURI());
    }

    @ExceptionHandler(PlanetClientException.class)
    public ResponseEntity<ErrorResponseDTO> handlePlanetClientException(PlanetClientException ex, HttpServletRequest request) {
        String message = "Error occurred while fetching planet data.";
        return buildErrorResponse(HttpStatus.BAD_GATEWAY, message, request.getRequestURI());
    }


    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(HttpStatus status, String message, String path) {
        ErrorResponseDTO error = new ErrorResponseDTO(status.value(), message, Instant.now(), path);
        return new ResponseEntity<>(error, status);
    }
}
