package br.edu.ifsp.testing.class04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class SummingIntsTest {

    private final SummingInts sut = new SummingInts();

    static Stream<org.junit.jupiter.params.provider.Arguments> provideValidCases() {
        return Stream.of(
                // exemplos do enunciado
                org.junit.jupiter.params.provider.Arguments.of(List.of(2, 3), List.of(4, 2), List.of(6, 5)),
                org.junit.jupiter.params.provider.Arguments.of(List.of(5, 5, 5), List.of(5, 0), List.of(6, 0, 5)),

                // listas vazias
                org.junit.jupiter.params.provider.Arguments.of(List.of(), List.of(), List.of(0)),

                // carry-over
                org.junit.jupiter.params.provider.Arguments.of(List.of(9), List.of(1), List.of(1, 0)),

                // tamanhos diferentes
                org.junit.jupiter.params.provider.Arguments.of(List.of(9, 9), List.of(1), List.of(1, 0, 0)),

                // full null
                org.junit.jupiter.params.provider.Arguments.of(null, null, null),

                // somente um null
                org.junit.jupiter.params.provider.Arguments.of(List.of(2, 3), null, null),

                // Somando zero
                org.junit.jupiter.params.provider.Arguments.of(List.of(0), List.of(0), List.of(0)),
                org.junit.jupiter.params.provider.Arguments.of(List.of(2, 3), List.of(0), List.of(2, 3)),

                // Múltiplos carrys
                org.junit.jupiter.params.provider.Arguments.of(List.of(9, 9, 9), List.of(1), List.of(1, 0, 0, 0)),

                // Tamanhos bem diferentes
                org.junit.jupiter.params.provider.Arguments.of(List.of(1), List.of(9, 9, 9, 9), List.of(1, 0, 0, 0, 0)),

                // Leading zeros (zeros à esquerda)
                org.junit.jupiter.params.provider.Arguments.of(List.of(0, 0, 1), List.of(9), List.of(1, 0))

                );
    }

    @DisplayName("Teste de soma válida de listas de dígitos")
    @ParameterizedTest(name = "[{index}] {0} + {1} = {2}")
    @MethodSource("provideValidCases")
    void shouldSumDigitListsCorrectly(List<Integer> left, List<Integer> right, List<Integer> expected) {
        assertThat(sut.add(left, right)).isEqualTo(expected);
    }
}
