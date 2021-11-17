package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailFieldValidator implements ConstraintValidator<EmailValidator, String> {

    private static final String EMAIL_REGEX="^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private Boolean notEmpty;
    private String messageNotEmpty;
    private String messageNoSpace;
    private String messageInvalidEmail;
    private boolean isSpace;

    @Override
    public void initialize(EmailValidator constraintAnnotation) {
        notEmpty = constraintAnnotation.notEmpty();
        messageNotEmpty = constraintAnnotation.messageNotEmpty();
        messageNoSpace = constraintAnnotation.messageNoSpace();
        messageInvalidEmail = constraintAnnotation.messageInvalidEmail();
        isSpace = constraintAnnotation.isSpace();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (notEmpty && value.isEmpty()) {
            context.buildConstraintViolationWithTemplate(messageNotEmpty).addConstraintViolation();
            return false;
        }
        if (!isSpace) {
            if (!value.matches("(?=\\S+$).+")) {
                context.buildConstraintViolationWithTemplate(messageNoSpace).addConstraintViolation();
                return false;
            }
        }
        if(!value.matches(EMAIL_REGEX)){
            context.buildConstraintViolationWithTemplate(messageInvalidEmail).addConstraintViolation();
            return false;
        }
        return false;
    }
}
