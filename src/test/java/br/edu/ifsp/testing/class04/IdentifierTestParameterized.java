package br.edu.ifsp.testing.class04;

import br.edu.ifsp.testing.class02.Identifier;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

public class IdentifierTestParameterized {

    private final Identifier sut = new Identifier();

    static Stream<org.junit.jupiter.params.provider.Arguments> providedCases() {
        return Stream.of(
                Arguments.of("", false),          // vazio -> inválido
                Arguments.of("a", true),          // letra sozinha -> válido
                Arguments.of("A1", true),         // letra + dígito -> válido
                Arguments.of("abc123", true),     // 6 chars -> válido
                Arguments.of("abcdefg", false),   // > 6 chars -> inválido
                Arguments.of("1abc", false),      // começa com dígito -> inválido
                Arguments.of("_abc", false),      // começa com especial -> inválido
                Arguments.of("ab c", false),      // contém espaço -> inválido
                Arguments.of("ab$1", false),      // contém especial -> inválido
                Arguments.of("z9", true)          // letra + dígito -> válido
        );
    }

    @DisplayName("Parameterized Identifier Test")
    @ParameterizedTest(name = "[{index}] - input = {0} | expected = {1}")
    @MethodSource("providedCases")
    void shouldTestIdentifier(String input, boolean expected) {
        assertThat(Identifier.isValid(input)).isEqualTo(expected);
    }

    @DisplayName("CsvSource Identifier Test")
    @CsvSource({"a,true", "A1,true", "abc123,true", "abcdefg,false", "1abc,false"})
    @ParameterizedTest
    void shouldInputCsvSourceAtIdentifierTest (String input, boolean expected) {
        assertThat(Identifier.isValid(input)).isEqualTo(expected);
    }

    @DisplayName("Repeated Test Identifier Test")
    @RepeatedTest(value = 3, name = "{displayName}: {currentRepetition} of {totalRepetitions}")
    void shouldRepeatIdentifierTest () {
        assertThat(Identifier.isValid("a")).isEqualTo(true);
    }
}
