package br.edu.ifsp.testing.class05.testability;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TestableXmasDiscountTest {
    @Test @DisplayName("Should provide discount on Christmas")
    void shouldProvideDiscountOnChristmas() {
        final LocalDate xmas = LocalDate.of(2025, 12, 25);
        final TestableXmasDiscount sut = new TestableXmasDiscount(xmas);
        final double obtained = sut.applyDiscount(1000);
        assertThat(obtained).isEqualTo(850.0);
    }

    @Test @DisplayName("Should not provide discount if is not Christmas")
    void shouldNotProvideDiscountIfIsNotChristmas() {
        final LocalDate notXmas = LocalDate.of(2025, 11, 25);
        final TestableXmasDiscount sut = new TestableXmasDiscount(notXmas);
        final double obtained = sut.applyDiscount(1000);
        assertThat(obtained).isEqualTo(1000);
    }
}