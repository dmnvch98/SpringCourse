package org.example.springmvc.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;

@RequiredArgsConstructor
@Getter
public class InvalidUserDataException extends Exception {
    private final BindingResult bindingResult;
    private final String pageToRedirect;
}
