package org.example.springmvc.dto;

import lombok.Data;
import org.example.springmvc.validations.flags.Credentials;
import org.example.springmvc.validations.flags.Unique;
import org.example.springmvc.validations.UsernameUnique;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotEmpty(groups = {Unique.class, Credentials.class})
    @NotNull(groups = {Unique.class, Credentials.class})
    @Length(groups = {Unique.class, Credentials.class},
            min = 3, max = 10, message = "username should be between 3 and 10 characters")
    @UsernameUnique(groups = Unique.class)
    private final String username;

    @NotEmpty(groups = {Unique.class, Credentials.class})
    @NotNull(groups = {Unique.class, Credentials.class})
    @Length(groups = {Unique.class, Credentials.class},
            min = 5, max = 15, message = "Password should be between 5 and 15 characters")
    private final String password;
}
