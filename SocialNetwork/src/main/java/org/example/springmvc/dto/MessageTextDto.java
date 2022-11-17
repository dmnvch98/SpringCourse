package org.example.springmvc.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MessageTextDto {
    @NotEmpty
    @NotNull
    private final String messageText;
}
