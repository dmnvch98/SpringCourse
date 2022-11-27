package org.example.springmvc.exceptions;

import lombok.*;
import org.springframework.validation.BindingResult;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InvalidUserDataException extends Exception {
    private BindingResult bindingResult;
    private String pageToRedirect;
}
