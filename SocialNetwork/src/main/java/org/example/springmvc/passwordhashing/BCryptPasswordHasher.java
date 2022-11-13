package org.example.springmvc.passwordhashing;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class BCryptPasswordHasher implements PasswordHasher {
    private final Hasher hasher;

    @Override
    public String hashPassword(String password) {
        return hasher.hashToString(12, password.toCharArray());
    }

    @Override
    public boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.verifyer().verify(password.getBytes(StandardCharsets.UTF_8),
                hashedPassword.getBytes(StandardCharsets.UTF_8))
                .verified;
    }
}
