package org.esgi.boissibook.infra.web;

import org.esgi.boissibook.kernel.exception.ConflictException;
import org.esgi.boissibook.kernel.exception.NotFoundBadRequestException;
import org.esgi.boissibook.kernel.exception.NotFoundException;
import org.esgi.boissibook.kernel.exception.SearchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger exceptionHandlerLogger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<UnhandledExceptionResponse> onUnhandled(RuntimeException ex) {
        exceptionHandlerLogger.error("Unhandled exception", ex);
        return ResponseEntity.internalServerError()
            .body(new UnhandledExceptionResponse(
                Arrays.toString(ex.getStackTrace()),
                ZonedDateTime.now(),
                ex.getMessage()
            ));
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        @NonNull HttpHeaders headers,
        @NonNull HttpStatus status,
        @NonNull WebRequest request
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

    @ExceptionHandler(NotFoundBadRequestException.class)
    public ResponseEntity<HandledExceptionResponse> notFoundBadRequest(NotFoundBadRequestException ex) {
        return ResponseEntity.badRequest().body(
            new HandledExceptionResponse(ZonedDateTime.now(), ex.getMessage())
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<HandledExceptionResponse> onConflict(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(new HandledExceptionResponse(
                ZonedDateTime.now(),
                ex.getMessage()
            ));
    }

    @ExceptionHandler(SearchException.class)
    public ResponseEntity<HandledExceptionResponse> onNotBadRequest(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new HandledExceptionResponse(
                ZonedDateTime.now(),
                ex.getMessage()
            ));
    }
}
