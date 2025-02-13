package br.edu.ifsp.testing.class04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.DayOfWeek;
import java.util.stream.Stream;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static org.assertj.core.api.Assertions.assertThat;

public class ParameterizedExampleTest {

    private Voter sut;

    @BeforeEach
    void setUp() {
        sut = new Voter();
    }

    // It also works for short, byte, log, float, double, char, String, and Class
    // Each value in ValueSource is bound to the method parameter
    @ParameterizedTest
    @ValueSource(ints = {16, 17, 70, 77}) // It is not possible to use more than one parameter
    @DisplayName("Should be an optional voter if 16, 17 or 70+")
    void shouldBeAnOptionalVoterIf1617Or70Plus(int age) {
        final boolean obtained = sut.isOptionalVoter(age);
        assertThat(obtained)
                .as("Is an optional voter")
                .isTrue();
    }

    @ParameterizedTest
    @NullAndEmptySource // it is possible to use only @NullSource or @EmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    void shouldReturnTrueForAllTypesOfBlankStrings(String input) {
        assertThat(input).isBlank();
    }

    @ParameterizedTest
    @EnumSource(value = DayOfWeek.class)
    void shouldDayOfWeekValueBeBetween1And7(DayOfWeek dayOfWeek) {
        assertThat(dayOfWeek.getValue()).isBetween(1, 7);
    }

    @ParameterizedTest
    @EnumSource(value = DayOfWeek.class, names = {"SATURDAY", "SUNDAY"})
    void shouldBeWeekendIfItIsSaturdayOrSunday(DayOfWeek dayOfWeek) {
        //Yeap, it is possible to use lambdas (Java 8+) in AssertJ. =D
        assertThat(dayOfWeek).matches(day -> day.equals(SATURDAY) || day.equals(SUNDAY));
    }

    @ParameterizedTest
    @ValueSource(strings = {"SATURDAY", "SUNDAY"})
        // Implicit Conversion
    void shouldDayOfWeekValueBeBetween1And7UsingStrings(DayOfWeek dayOfWeek) {
        assertThat(dayOfWeek).matches(day -> day.equals(SATURDAY) || day.equals(SUNDAY));
    }

    @ParameterizedTest
    @CsvSource({"FOUR,THREE", "SEVEN,QUEEN", "QUEEN,JACK", "JACK,KING", "KING,ACE"})
    @DisplayName("Should return correct winner for non manilhas")
    void shouldReturnCorrectWinnerCardForNonManilhas(Rank lowerRank, Rank higherRank) { //Enuns
        final Card lowerCard = Card.of(lowerRank, Suit.SPADES);
        final Card higherCard = Card.of(higherRank, Suit.SPADES);
        final Card vira = Card.of(Rank.FIVE, Suit.SPADES);
        assertThat(lowerCard.compareValueTo(higherCard, vira)).isNegative();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    @DisplayName("Should correctly get the next rank value")
    void shouldCorrectlyGetTheNextRankValue(Rank current, Rank next) {
        assertThat(current.next()).isEqualTo(next);
    }

    @ParameterizedTest
    @MethodSource(value = "provideDataToToStringTest")
    void shouldCorrectlyToString(Rank rank, Suit suit, String toStringText) {
        assertThat(Card.of(rank, suit).toString()).isEqualTo(toStringText);
    }

    static Stream<Arguments> provideDataToToStringTest() { // List<Arguments> is ok too.
        return Stream.of(
                Arguments.of("SEVEN", "DIAMONDS", "[7D]"),
                Arguments.of("ACE", "HEARTS", "[AH]"),
                Arguments.of("QUEEN", "CLUBS", "[QC]"),
                Arguments.of("JACK", "SPADES", "[JS]"),
                Arguments.of("KING", "SPADES", "[KS]"));
    }

    @DisplayName("Should correctly toString()")
    @ParameterizedTest(name = "[{index}]: Card.of({0}, {1}).toString() should be {2}")
    @MethodSource(value = "provideDataToToStringTest")
    void shouldCorrectlyToStringWithDisplayName(Rank rank, Suit suit, String toStringText) { //{0},{1},{2}
        assertThat(Card.of(rank, suit).toString()).isEqualTo(toStringText);
    }
}
