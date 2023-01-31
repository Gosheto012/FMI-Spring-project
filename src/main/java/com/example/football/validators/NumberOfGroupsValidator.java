package com.example.football.validators;

import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberOfGroupsValidator implements ConstraintValidator<ValidNumberOfGroups, Long> {

    Set<Long> acceptedNumberOfGroups = Set.of(2L,4L,8L);

    @Override
    public boolean isValid(Long numberOfGroups, ConstraintValidatorContext constraIntegerValidatorContext) {
        if(!acceptedNumberOfGroups.contains(numberOfGroups)) {
            constraIntegerValidatorContext.disableDefaultConstraintViolation();
            constraIntegerValidatorContext.buildConstraintViolationWithTemplate("The accepted numbers of groups are: "
                    + acceptedNumberOfGroups.toString());
            return false;
        }
        return true;
    }
}
