package com.fsm.facedetectionservice.exception;

import io.minio.errors.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GlobalExceptionHandler
        extends ResponseEntityExceptionHandler {

    @Override
    @ExceptionHandler(
            value = {
                    ErrorResponseException.class, InsufficientDataException.class,
                    InternalException.class, InvalidKeyException.class,
                    InvalidResponseException.class, IOException.class,
                    NoSuchAlgorithmException.class, ServerException.class,
                    XmlParserException.class
            }
    )
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
