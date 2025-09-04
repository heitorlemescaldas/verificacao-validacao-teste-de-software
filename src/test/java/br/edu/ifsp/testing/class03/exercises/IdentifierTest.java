package br.edu.ifsp.testing.class03.exercises;

import br.edu.ifsp.testing.class02.Identifier;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class IdentifierTest {

    @Test
    void testEmptyString() {
        assertThat(Identifier.isValid("")).isFalse(); // T1
    }

    @Test
    void testSingleLetter() {
        assertThat(Identifier.isValid("a")).isTrue(); // T2
    }

    @Test
    void testLetterFollowedByDigit() {
        assertThat(Identifier.isValid("A1")).isTrue(); // T3
    }

    @Test
    void testMaxLengthValid() {
        assertThat(Identifier.isValid("abc123")).isTrue(); // T4
    }

    @Test
    void testTooLong() {
        assertThat(Identifier.isValid("abcdefg")).isFalse(); // T5
    }

    @Test
    void testStartsWithDigit() {
        assertThat(Identifier.isValid("1abc")).isFalse(); // T6
    }

    @Test
    void testStartsWithSpecialChar() {
        assertThat(Identifier.isValid("_abc")).isFalse(); // T7
    }

    @Test
    void testContainsSpace() {
        assertThat(Identifier.isValid("ab c")).isFalse(); // T8
    }

    @Test
    void testContainsSpecialChar() {
        assertThat(Identifier.isValid("ab$1")).isFalse(); // T9
    }

    @Test
    void testLetterAndDigitValid() {
        assertThat(Identifier.isValid("z9")).isTrue(); // T10
    }
}
