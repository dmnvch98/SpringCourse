package org.example.springmvc.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.dto.UserDto;
import org.example.springmvc.exceptions.InvalidCredentialException;
import org.example.springmvc.exceptions.InvalidUserDataException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ExceptionMapper {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(final Exception exception) {
        log.error("The following exception was thrown: " + exception);
        ModelAndView model = new ModelAndView();
        model.setViewName("error");
        return model;
    }

    @ExceptionHandler(InvalidUserDataException.class)
    public ModelAndView handeException(final InvalidUserDataException exception) {
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        log.error("User entered invalid data: " + errors);
        ModelAndView model = new ModelAndView();
        model.addObject("errors", errors);
        model.addObject("userDto", new UserDto());
        model.setViewName(exception.getPageToRedirect());
        return model;
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ModelAndView handleException(final InvalidCredentialException exception) {
        ModelAndView model = new ModelAndView();
        model.addObject("errors", Collections.singletonList(exception.getMessage()));
        model.setViewName("sign_in");
        return model;
    }
}
