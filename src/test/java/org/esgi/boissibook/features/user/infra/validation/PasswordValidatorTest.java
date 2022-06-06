package org.esgi.boissibook.features.user.infra.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordValidatorTest {

    private final PasswordValidator validator = new PasswordValidator();

    private static Stream<Arguments> validatorData() {
        return Stream.of(
                Arguments.of("Test1", true),
                Arguments.of("tTesT1", true),
                Arguments.of("tTes|1", true),
                Arguments.of("te2st", false),
                Arguments.of("tEst", false)
        );
    }


    @ParameterizedTest(name = "Password {0} should be {1}")
    @MethodSource("validatorData")
    void shouldValidate(String input, boolean expected) {
        assertThat(validator.isValid(input, null)).isEqualTo(expected);
    }
}