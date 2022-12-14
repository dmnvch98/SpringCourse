package org.example.springmvc.facades;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.exceptions.InvalidCredentialException;
import org.example.springmvc.service.UserService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationFacade {
    private final UserService userService;

    public boolean signIn(final String username, final String password) throws InvalidCredentialException {
        log.info("Attempt to login with username: [{}]", username);
        if (userService.verifyUser(username, password)) {
            log.info("User logged in successfully: username: [{}]", username);
            return true;
        } else {
            log.warn("User provides invalid data. Username [{}]", username);
            throw new InvalidCredentialException();
        }
    }

    public boolean signUp(final String username, final String password, final String role) {
        log.info("Attempt to signup with username: [{}]", username);
        try {
            userService.save(username, password, role, new Date());
            log.info("User signed up successfully: username [{}]", username);
            return true;
        } catch (IOException e) {
            log.info("User run into an issue during signup. User: [{}]", username);
            log.error(e.getMessage());
        }
        return false;
    }
}
