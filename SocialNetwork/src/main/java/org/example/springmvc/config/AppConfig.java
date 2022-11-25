package org.example.springmvc.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Configuration
@PropertySource("classpath:application.yaml")
public class AppConfig {

    @Value("${salt}")
    private String salt;

    @Bean
    public Hasher hasher() {
        return BCrypt.with(new SecureRandom(salt.getBytes(StandardCharsets.UTF_8)));
    }
}
