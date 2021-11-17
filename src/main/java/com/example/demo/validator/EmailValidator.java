package com.example.demo.validator;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {EmailFieldValidator.class})
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface EmailValidator {

    String messageNotEmpty() default "Field can't be empty";

    String messageNoSpace() default "Space not allowed";

    String messageInvalidEmail() default "Invalid email";

    String message() default  "Enter email id";

    boolean isSpace() default false;

    boolean notEmpty() default true;

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
