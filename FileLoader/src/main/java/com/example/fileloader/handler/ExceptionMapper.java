package com.example.fileloader.handler;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionMapper {
    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handeException(final IOException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
