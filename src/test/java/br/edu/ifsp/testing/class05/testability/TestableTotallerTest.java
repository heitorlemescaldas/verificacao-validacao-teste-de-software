package br.edu.ifsp.testing.class05.testability;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class TestableTotallerTest {
    @Test
    @DisplayName("Should calculate total")
    void shouldCalculateTotal() {
        final TestableTotaller sut = new TestableTotaller();
        final List<Integer> numbers = IntStream
                .rangeClosed(1, 10_000)
                .boxed()
                .toList();

        int result = sut.syncTotal(numbers);
        assertThat(result).isEqualTo(50_005_000);
    }
}