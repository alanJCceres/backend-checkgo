package com.acmsoft.checkgo.exception;

import com.acmsoft.checkgo.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // 1. EXCEPCIONES PERSONALIZADAS DE NEGOCIO
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
                ResourceNotFoundException ex, HttpServletRequest request){
        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRuleException(
            BusinessRuleException ex, HttpServletRequest request) {

        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // 2. SOBREESCRITURA DE EXCEPCIONES INTERNAS DE SPRING
    //metodo cuando falla en valid como notnull, notblank, etc
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request){
        List<String> errors = new ArrayList<>();
        for(FieldError error: ex.getBindingResult().getFieldErrors()){
            errors.add(error.getField()+": "+error.getDefaultMessage());
        }
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.value())
                .error(((HttpStatus) status).getReasonPhrase())
                .message("Error de validación en los datos enviados.")
                .path(extractPath(request))
                .details(errors)
                .build();
        return new ResponseEntity<>(errorResponse, headers, status);
    }
    //metodo para errores estandars como 405, 400, etc
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.value())
                .error(HttpStatus.valueOf(status.value()).getReasonPhrase())
                .message(ex.getMessage()) // Spring inyecta mensajes como "Request method 'GET' not supported"
                .path(extractPath(request))
                .build();

        return new ResponseEntity<>(errorResponse, headers, status);
    }

    // 3. EXCEPCION PERSONALIZADA PARA ERRORES INESPERADOS (ERROR 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(
            Exception ex, HttpServletRequest request) {

        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Ocurrió un error interno en el servidor. Contacte al soporte.")
                .path(request.getRequestURI())
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //metodo auxiliar
    private String extractPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }
}
