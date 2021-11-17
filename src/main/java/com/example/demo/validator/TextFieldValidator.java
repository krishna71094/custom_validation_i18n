package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TextFieldValidator implements ConstraintValidator<TextValidator, Object> {

    private static final String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private Boolean notEmpty;
    private boolean acceptNumber;
    private Integer min;
    private Integer max;
    private String messageNotEmpty;
    private String invalidValue;
    private String messageLength;
    private String messageNoSpace;
    private boolean isSpace;
    private boolean isEmail;


    @Override
    public void initialize(TextValidator constraintAnnotation) {
        notEmpty = constraintAnnotation.notEmpty();
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
        acceptNumber = constraintAnnotation.acceptNumber();
        messageNotEmpty = constraintAnnotation.messageNotEmpty();
        messageLength = constraintAnnotation.messageLength();
        messageNoSpace = constraintAnnotation.messageNoSpace();
        isSpace = constraintAnnotation.isSpace();
        invalidValue = constraintAnnotation.invalidValue();
        isEmail = constraintAnnotation.isEmail();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (value instanceof String) {
            String res = String.valueOf(value);
            return validationForString(res, context);
        } else if (value instanceof Integer) {
            String res = String.valueOf(value);
            return validationForInteger(res, context);
        }
        return true;
    }

    private boolean validationForString(String res, ConstraintValidatorContext context) {
        if (notEmpty && res.isEmpty()) {
            context.buildConstraintViolationWithTemplate(messageNotEmpty).addConstraintViolation();
            return false;
        }
        if (res.equalsIgnoreCase(null)) {
            context.buildConstraintViolationWithTemplate(invalidValue).addConstraintViolation();
            return false;
        }
        if ((min > 0 || max < Integer.MAX_VALUE) && (res.length() < min || res.length() > max)) {
            context.buildConstraintViolationWithTemplate(messageLength).addConstraintViolation();
            return false;
        }
        if (!isSpace) {
            if (!res.matches("(?=\\S+$).+")) {
                context.buildConstraintViolationWithTemplate(messageNoSpace).addConstraintViolation();
                return false;
            }
        }
        if (isEmail) {
            if (!res.matches(EMAIL_REGEX)) {
                context.buildConstraintViolationWithTemplate(invalidValue).addConstraintViolation();
                return false;
            }
        }
        if (!acceptNumber) {
            if (!res.matches("[a-zA-Z]+")) {
                context.buildConstraintViolationWithTemplate(invalidValue).addConstraintViolation();
                return false;
            }
        }
        return true;
    }

    private boolean validationForInteger(String res, ConstraintValidatorContext context) {
        if (notEmpty && res.isEmpty()) {
            context.buildConstraintViolationWithTemplate(messageNotEmpty).addConstraintViolation();
            return false;
        }
        if (isEmail) {
            context.buildConstraintViolationWithTemplate(invalidValue).addConstraintViolation();
            return false;
        }
        if ((min > 0 || max < Integer.MAX_VALUE) && (res.length() < min || res.length() > max)) {
            context.buildConstraintViolationWithTemplate(messageLength).addConstraintViolation();
            return false;
        }
        if (!res.matches("[0-9]+") || res.equalsIgnoreCase(null)) {
            context.buildConstraintViolationWithTemplate(invalidValue).addConstraintViolation();
            return false;
        }
        return true;
    }

}
