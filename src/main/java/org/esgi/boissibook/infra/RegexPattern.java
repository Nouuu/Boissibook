package org.esgi.boissibook.infra;

import java.util.regex.Pattern;

public enum RegexPattern {
    UPPERCASE_REGEX("[A-Z]"),
    DIGIT_REGEX("[\\d]"),
    ANY_REGEX(".*");

    private final Pattern pattern;

    RegexPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public Pattern pattern() {
        return pattern;
    }
}
