package org.example.springmvc.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class InvalidCredentialException extends Exception {
    private final String message = "The user with the entered data does not exist";
}
