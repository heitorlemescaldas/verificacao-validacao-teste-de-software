package br.edu.ifsp.testing.class08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DiscountCalculatorTest {
    @ParameterizedTest
    @CsvSource({"100.0, true, false, 90",
                "100.0, false, true, 90",
                "100.0, false, false, 100.0"})
    void testMemberGetsDiscount(double price, boolean isMember,
                                boolean isHoliday, double expected) {
        DiscountCalculator sut = new DiscountCalculator();
        double obtained = sut.calculateDiscount(price, isMember, isHoliday);
        assertThat(obtained).isEqualTo(expected);
    }
}