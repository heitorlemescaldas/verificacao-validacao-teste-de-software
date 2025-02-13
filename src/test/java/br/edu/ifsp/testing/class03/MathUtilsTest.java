package br.edu.ifsp.testing.class03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MathUtilsTest {

    @Nested // This annotation will be discussed later
    @DisplayName("Testing invalid equivalence classes")
    class TestingInvalidClasses {
        @Test
        @DisplayName("Should handle division by zero")
        void shouldHandleDivisionByZero() {
            final MathUtils sut = new MathUtils();
            final double obtained = sut.divide(3, 0);
            assertEquals(Double.POSITIVE_INFINITY, obtained);
        }
    }

    @Test
    void shouldResultBeGreaterThanOneIfNumeratorIsGreaterThanDenominator() {
        final MathUtils sut = new MathUtils(); //Given
        final double obtained = sut.divide(5, 2); //When
        Assertions.assertTrue(obtained > 1); //Then
    }

    @Test
    void shouldKeepDecimalsIfNumeratorAndDenominatorAreIntegers() {
        final MathUtils sut = new MathUtils();
        final double obtained = sut.divide(3, 2);
        //use static import to directly access all assert methods of Assertions class.
        assertEquals(1.5, obtained);
    }

    @Test
    void shouldHandleDivisionByZero() {
        final MathUtils sut = new MathUtils();
        final double obtained = sut.divide(3, 0); // ops!
        assertEquals(Double.POSITIVE_INFINITY, obtained); // not executed!
    }

    @Test
    @DisplayName("What we write in the display name produces the method name")
    void whatWeWriteInTheDisplayNameProducesTheMethodName() {
        
        org.junit.jupiter.api.Assertions.fail();
    }


}