package com.example.demo;

import com.example.demo.validator.TextValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestingDto {

    @TextValidator(min = 1,max = 12,isSpace = false,acceptNumber = false)
    public String test;

}
