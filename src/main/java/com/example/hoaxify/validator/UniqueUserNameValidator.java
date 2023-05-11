package com.example.hoaxify.validator;

import com.example.hoaxify.annotation.UniqueUserNameConstraint;
import com.example.hoaxify.model.User;
import com.example.hoaxify.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserNameConstraint, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        User persistedUser = userRepository.findByUserName(userName);
        if (persistedUser == null) {
            return true;
        }
        return false;
    }
}
