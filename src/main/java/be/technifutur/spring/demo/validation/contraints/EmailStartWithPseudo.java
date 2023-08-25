package be.technifutur.spring.demo.validation.contraints;

import be.technifutur.spring.demo.validation.validators.EmailStartWithPseudoValidator;
import be.technifutur.spring.demo.validation.validators.EmailUniqueValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailStartWithPseudoValidator.class)
public @interface EmailStartWithPseudo {
    String message() default "email should start with the pseudo";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
