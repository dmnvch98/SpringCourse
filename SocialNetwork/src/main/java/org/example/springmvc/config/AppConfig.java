package org.example.springmvc.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {
        "org.example.springmvc"
})
public class AppConfig {
    @Autowired
    private Environment environment;

    @Bean
    public Hasher hasher () {
        String secret = environment.getRequiredProperty("hasher.salt");
        return BCrypt.with(new SecureRandom(secret.getBytes(StandardCharsets.UTF_8)));
    }
}
