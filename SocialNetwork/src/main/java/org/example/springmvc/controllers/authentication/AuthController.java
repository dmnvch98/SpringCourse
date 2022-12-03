package org.example.springmvc.controllers.authentication;


import lombok.RequiredArgsConstructor;
import org.example.springmvc.config.security.jwt.Jwt;
import org.example.springmvc.dto.AuthResultDto;
import org.example.springmvc.dto.CredentialsDto;
import org.example.springmvc.exceptions.InvalidCredentialException;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final Jwt jwt;

    @PostMapping
    public AuthResultDto authorize(@RequestBody final CredentialsDto credentials) throws InvalidCredentialException {
        if (userService.verifyUser(credentials.getUsername(), credentials.getPassword())) {
            User user = userService.getUser(credentials.getUsername());
            String token = jwt.generateToken(user.getUsername());
            return new AuthResultDto(token);
        } else {
            return null;
        }
    }

}
