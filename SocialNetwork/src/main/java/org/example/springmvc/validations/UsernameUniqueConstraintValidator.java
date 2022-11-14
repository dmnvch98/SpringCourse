package org.example.springmvc.validations;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@RequiredArgsConstructor
public class UsernameUniqueConstraintValidator implements ConstraintValidator<UsernameUnique, String> {
    private final UserService userService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.isExist(s);
    }
}
