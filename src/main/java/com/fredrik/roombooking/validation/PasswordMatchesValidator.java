package com.fredrik.roombooking.validation;

import com.fredrik.roombooking.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context){
        UserDto userDto = (UserDto) object;
        return userDto.getPassword().equals(userDto.getPasswordConfirm());
    }

}
