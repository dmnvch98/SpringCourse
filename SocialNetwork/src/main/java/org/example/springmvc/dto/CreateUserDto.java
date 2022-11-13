package org.example.springmvc.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private final String username;
    private final String password;
}
