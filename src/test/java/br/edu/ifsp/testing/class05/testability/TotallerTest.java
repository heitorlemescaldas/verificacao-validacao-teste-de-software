package br.edu.ifsp.testing.class05.testability;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class TotallerTest {

    private int result = 0;

    @Test
    @DisplayName("Should calculate total")
    void shouldCalculateTotal() {
        final Totaller sut = new Totaller();
        final List<Integer> numbers = IntStream
                .rangeClosed(1, 10_000)
                .boxed()
                .toList();

        // Once the total is calculated, it will be stored in the result variable.
        sut.asyncTotal(numbers, total -> result = total);

        assertThat(result).isEqualTo(50_005_000);
    }
}