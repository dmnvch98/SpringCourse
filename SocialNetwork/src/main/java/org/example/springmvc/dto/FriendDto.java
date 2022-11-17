package org.example.springmvc.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class FriendDto {
    @NotEmpty
    @NotNull
    private String friendUsername;
}
