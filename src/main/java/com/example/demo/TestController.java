package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@Slf4j
public class TestController {

    @Autowired
    MessageSource messageSource;

    @PostMapping("/test")
    public ResponseEntity<?> test(@Valid @RequestBody TestingDto testing,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getErrorsResponse(bindingResult);
        }
        return ResponseEntity.ok("valid");
    }

    private ResponseEntity<?> getErrorsResponse(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ObjectError> globalErrors =
                bindingResult.getGlobalErrors();
        List<FieldErrorDTO> errors =
                new ArrayList<>(fieldErrors.size() + globalErrors.size());
        for (FieldError fieldError : fieldErrors) {
            FieldErrorDTO error = new FieldErrorDTO(fieldError.getField(),
                    messageSource.getMessage(fieldError.getDefaultMessage(), null, Locale.US), fieldError.getCode());
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            FieldErrorDTO error = new FieldErrorDTO(objectError.getObjectName(),
                    messageSource.getMessage(objectError.getDefaultMessage(), null,Locale.US), objectError.getCode());
            errors.add(error);
        }
        return ResponseEntity.badRequest().body(errors);
    }

}
