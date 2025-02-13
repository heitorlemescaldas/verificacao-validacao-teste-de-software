package br.edu.ifsp.testing.class03;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertJExamplesTest {


    @Test
    void testingObjectAssertions() {
        String someString = "Hello";
        String otherString = "World";

        assertEquals("Hello World", someString + " " + otherString); // JUnit
        assertThat(someString + " " + otherString).isEqualTo("Hello World"); // AssertJ
    }

    @Test
    @DisplayName("Testing Boolean assertions")
    void testingBooleanAssertions() {
        final String someString = " ";
        assertThat(someString.isEmpty()).isFalse();
        assertThat(someString.isBlank()).isTrue();
    }

    @Test
    @DisplayName("Testing numeric assertions")
    void testingNumericAssertions() {
        double a = 10.0;
        double b = 3.0;
        assertThat(a + b).isEqualTo(13.0);
        assertThat(a + Double.POSITIVE_INFINITY).isInfinite();
        // it is also possible to chain assertions
        assertThat(a / b).isGreaterThan(3).isCloseTo(3.33, offset(0.01));
        assertThat(a / 10).isOne();
        assertThat(0 / b).isZero();
    }

    @Test
    @DisplayName("Testing String assertions")
    void testingStringAssertions() {
        final String email = "some@email.com";
        final String admin = "Admin: Ada Lovelace";
        assertThat(email)
                .containsOnlyOnce("@")
                .contains(".")
                .hasSizeBetween(5, 254);
        assertThat(admin)
                .startsWith("Admin:")
                .matches(Pattern.compile("(:|\\w|\\s){7,}"));
    }


    @Test
    @DisplayName("Testing Iterables assertions")
    void testingIterablesAssertions() {
        final var livingLegends = Arrays.asList("Martin Fowler", "Robert Martin",
                "Kent Beck", "Grady Booch", "Andy Hunt");
        assertThat(livingLegends)
                .isNotEmpty()
                .contains("Martin Fowler", "Robert Martin")
                .doesNotContain("Grace Hopper") // legend, but already deceased
                .doesNotContainNull()
                .hasSameSizeAs(List.of(1, 2, 3, 4, 5));
    }

    @Test
    @DisplayName("Testing date assertions")
    void testingDateAssertions() {
        final LocalDate birthDate = LocalDate.of(1986, Month.JANUARY, 27);
        assertThat(birthDate)
                .isBefore(LocalDate.now())
                .isBetween(birthDate.minusDays(1), birthDate.plusDays(1))
                .hasDayOfMonth(27)
                .hasMonth(Month.JANUARY)
                .hasYear(1986);
    }

    @Test
    @DisplayName("Testing exceptions")
    void testingExceptions() {
        assertThatNoException().isThrownBy(() -> "Hello".concat("World"));
        // notice that it asserts instead of propagate
        assertThatThrownBy(() -> "Hello".concat(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Cannot invoke \"String.isEmpty()\" because \"str\" is null");
        // the remaining assert the type of the exception, not the exception itself.
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> "Hello".concat(null));
        assertThatNullPointerException().isThrownBy(() -> "Hello".concat(null));
    }

    @Test
    void shouldTestNormalTestResultDescription() {
        // bad assertion, just for sake of example
        assertThat(1 + 1 == 3).isTrue();
    }

    @Test
    void shouldTestBetterTestResultDescription() {
        assertThat(1 + 1 == 3)
                .as("%d + %d == %d", 1, 1, 3)
                .isTrue();
    }

    @Test
    void shouldTestBirthdayAttributes() {
        final LocalDate birthday = LocalDate.of(2025, Month.JANUARY, 27);
        assertThat(birthday.getDayOfWeek()).as("Day of Week").isEqualTo(DayOfWeek.SATURDAY); // actual value is MONDAY
        assertThat(birthday.getDayOfYear()).as("Day of Year").isEqualTo(100); // actual value is 27
    }

    @Test
    void shouldSoftlyTestBirthdayAttributes() {
        final LocalDate birthday = LocalDate.of(2025, Month.JANUARY, 27);
        SoftAssertions softly = new SoftAssertions();

        // it includes, but do not execute
        softly.assertThat(birthday.getDayOfWeek()).as("Day of Week").isEqualTo(DayOfWeek.SATURDAY);
        softly.assertThat(birthday.getDayOfYear()).as("Day of Year").isEqualTo(100);
        softly.assertAll(); // execute all assertions at once
    }


}