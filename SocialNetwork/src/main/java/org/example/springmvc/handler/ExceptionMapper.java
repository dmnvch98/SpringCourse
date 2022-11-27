package org.example.springmvc.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.dto.UserDto;
import org.example.springmvc.exceptions.InvalidCredentialException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@ControllerAdvice
@Slf4j
public class ExceptionMapper {
    @ExceptionHandler(BindException.class)
    public ModelAndView handeException(final BindException exception) {
    List<String> errors = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(x -> Optional.ofNullable(x.getDefaultMessage()).orElse("unknown error"))
            .toList();
    log.error("User entered invalid data: " + errors);
    ModelAndView model = new ModelAndView();
    model.addObject("errors", errors);
    model.addObject("userDto", new UserDto());
    model.setViewName("sign_up");
    return model;
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ModelAndView handleException(final InvalidCredentialException exception) {
        ModelAndView model = new ModelAndView();
        model.addObject("errors", exception.getMessage());
        model.addObject("userDto", new UserDto());
        model.setViewName("sign_in");
        return model;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleException(final MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> Optional.ofNullable(x.getDefaultMessage()).orElse("unknown error"))
                .toList();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
