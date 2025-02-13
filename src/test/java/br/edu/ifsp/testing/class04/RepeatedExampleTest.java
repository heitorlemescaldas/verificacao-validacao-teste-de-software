package br.edu.ifsp.testing.class04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class RepeatedExampleTest {

    @RepeatedTest(3)
    @DisplayName("Should one plus one be two")
    void shouldOnePlusOneBeTwo() {
        assertThat(Math.addExact(1, 1)).as("1 + 1 = ?").isEqualTo(2);
    }

    @RepeatedTest(value = 3, name = "{displayName}: {currentRepetition} of {totalRepetitions}")
    @DisplayName("Should one plus one be two")
    void shouldOnePlusOneBeTwoWithDisplayName() {
        assertThat(Math.addExact(1, 1)).as("1 + 1 = ?").isEqualTo(2);
    }
}
