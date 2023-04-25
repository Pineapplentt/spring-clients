package com.jazztech.exception.handler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    public ProblemDetail handleClientNotFound(ClientNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), e.getMessage());
        problemDetail.setType(URI.create(""));
        problemDetail.setTitle(e.getClass().getSimpleName());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ProblemDetail handleClientAlreadyExists(ClientAlreadyExistsException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(409), e.getMessage());
        problemDetail.setType(URI.create(""));
        problemDetail.setTitle("Usuário já existe");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(CepNotFoundException.class)
    public ProblemDetail handleCepNotFound(CepNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), e.getMessage());
        problemDetail.setType(URI.create(""));
        problemDetail.setTitle("CEP não encontrado");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
    @ExceptionHandler(InvalidCepException.class)
    public ProblemDetail handleInvalidCep(InvalidCepException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), e.getMessage());
        problemDetail.setType(URI.create(""));
        problemDetail.setTitle("CEP inválido, verifique a ortografia e tente novamente");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
