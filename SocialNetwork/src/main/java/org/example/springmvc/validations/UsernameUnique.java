package org.example.springmvc.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameUniqueConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameUnique {
    String message() default "Username already exists!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
