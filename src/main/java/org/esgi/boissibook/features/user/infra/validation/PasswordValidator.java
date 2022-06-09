package org.esgi.boissibook.features.user.infra.validation;

import org.esgi.boissibook.infra.RegexPattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private static final List<Pattern> PATTERNS = List.of(
        RegexPattern.UPPERCASE_REGEX.pattern(),
        RegexPattern.ANY_REGEX.pattern(),
        RegexPattern.DIGIT_REGEX.pattern()
    );


    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isBlank()) {
            return true;
        }

        return PATTERNS.stream().allMatch(pattern -> pattern.matcher(password).find());
    }
}

