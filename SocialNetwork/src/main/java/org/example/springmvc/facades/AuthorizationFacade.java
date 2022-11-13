package org.example.springmvc.facades;

import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.passwordhashing.PasswordHasher;
import org.example.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Log4j2
@RequiredArgsConstructor
@Component
public class AuthorizationFacade {
    private final UserService userService;

    public boolean signIn(String username, String password) {
        log.info("Attempt to login with username: " + username);
        if (userService.verifyUser(username, password)) {
            log.info("User logged in successfully: username: [{}]", username);
            return true;
        } else {
            log.info("User failed to login: username: [{}]", username);
            return false;
        }
    }

    public boolean signUp(String username, String password) {
        log.info("Attempt to signup with username: " + username);
        if (!(username.isEmpty() && password.isEmpty())) {
            try {
                String hashedPassword = userService.getPasswordHasher().hashPassword(password);
                userService.save(username, hashedPassword, "USER", new Date());
                log.info("User signed up successfully: username [{}]", username);
                return true;
            } catch (IOException e) {
                log.error("Error during signup: " + e);
            }
        }
        return false;
    }

    private void setSessionAttributes(final HttpServletRequest req, final String username) {
        req.getSession().setAttribute("isLoggedIn", true);
        req.getSession().setAttribute("username", username);
    }
}
