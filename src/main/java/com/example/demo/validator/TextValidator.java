package com.example.demo.validator;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { TextFieldValidator.class })
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface TextValidator {

    String messageNotEmpty() default "{error.message.not.empty}";

    String messageLength() default "{error.message.length}";

    String messageNoSpace() default "{error.space.found}";

    String invalidValue() default "{error.invalid.data}";

    String message() default "{error.wrong.field}";

    boolean notEmpty() default true;

    boolean isSpace() default true;

    boolean acceptNumber() default true;

    boolean isEmail() default false;

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
