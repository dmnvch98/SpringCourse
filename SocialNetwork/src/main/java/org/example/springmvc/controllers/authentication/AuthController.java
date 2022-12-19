package org.example.springmvc.controllers.authentication;


import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.example.springmvc.config.security.jwt.Jwt;
import org.example.springmvc.dto.JwtResponse;
import org.example.springmvc.dto.CredentialsDto;
import org.example.springmvc.dto.RefreshTokenDto;
import org.example.springmvc.exceptions.InvalidCredentialException;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
@Hidden
public class AuthController {

    private final UserService userService;
    private final Jwt jwt;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> authorize(@RequestBody final CredentialsDto credentials) throws InvalidCredentialException {
        if (userService.verifyUser(credentials.getUsername(), credentials.getPassword())) {
            User user = userService.getUser(credentials.getUsername());
            String accessToken = jwt.generateAccessToken(user);
            String refreshToken = jwt.generateRefreshToken(user.getUsername());
            user.setRefreshToken(refreshToken);
            userService.updateUser(user);
            return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("access")
    public ResponseEntity<JwtResponse> getAccessToken(@RequestBody final RefreshTokenDto dto) {
        if (jwt.validateRefreshToken(dto.getRefreshToken())) {
            String username = jwt.getLoginFromRefreshToken(dto.getRefreshToken());
            User user = userService.getUser(username);
            if (user.getRefreshToken() != null && user.getRefreshToken().equals(dto.getRefreshToken())) {
                String accessToken = jwt.generateAccessToken(user);
                return ResponseEntity.ok(new JwtResponse(accessToken, null));
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> refreshTokens(@RequestBody final RefreshTokenDto dto) {
        if (jwt.validateRefreshToken(dto.getRefreshToken())) {
            String username = jwt.getLoginFromRefreshToken(dto.getRefreshToken());
            User user = userService.getUser(username);
            if (user.getRefreshToken() != null && user.getRefreshToken().equals(dto.getRefreshToken())) {
                String accessToken = jwt.generateAccessToken(user);
                String refreshToken = jwt.generateRefreshToken(username);
                user.setRefreshToken(refreshToken);
                userService.updateUser(user);
                return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
            }
        }
        return ResponseEntity.noContent().build();
    }

}
