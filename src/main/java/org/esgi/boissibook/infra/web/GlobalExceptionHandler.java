package org.esgi.boissibook.infra.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<UnhandledExceptionResponse> onUnhandled(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseEntity.internalServerError()
                .body(new UnhandledExceptionResponse(
                        Arrays.toString(ex.getStackTrace()),
                        ZonedDateTime.now(),
                        ex.getMessage()
                ));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request
    ) {
        return ResponseEntity.badRequest()
                .body(new HandledExceptionResponse(
                        ZonedDateTime.now(),
                        ex.getBindingResult()
                                .getAllErrors()
                                .stream()
                                .map(error -> String.format("%s: %s",
                                        ((FieldError) error).getField(),
                                        error.getDefaultMessage())
                                )
                                .collect(Collectors.joining(", "))
                ));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HandledExceptionResponse> onNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new HandledExceptionResponse(
                        ZonedDateTime.now(),
                        ex.getMessage()
                ));
    }
}
