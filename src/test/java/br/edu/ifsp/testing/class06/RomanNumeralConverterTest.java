package br.edu.ifsp.testing.class06;

import br.edu.ifsp.testing.class06.slides.RomanNumeralConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RomanNumeralConverterTest {
    @ParameterizedTest(name = "Should convert {0} in Roman to {1} in Arabic.")
    @CsvSource({"I,1", "V,5", "X,10","L,50", "C, 100", "D, 500", "M, 1000", "II,2", "CCXCIV, 294"})
    void shouldConvertRomanToArabic(String romanNumeral, int expectedNumber) {
        RomanNumeralConverter roman = new RomanNumeralConverter();
        int convertedNumber = roman.convert(romanNumeral);
        assertThat(convertedNumber).isEqualTo(expectedNumber);
    }
}