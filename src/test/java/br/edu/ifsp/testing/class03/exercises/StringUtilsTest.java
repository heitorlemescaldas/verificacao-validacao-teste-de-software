package br.edu.ifsp.testing.class03.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    private  StringUtils sut;

    @BeforeEach
    void setup(){
        sut = new StringUtils();
    }

    @Test
    @DisplayName("Should a null str return null")
    void shouldANullStrReturnNull() {
        final String[] obtained = sut.substringsBetween(null, "jao", "maria");
        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("Should a empty str return empty string array")
    void shouldAEmptyStrReturnEmptyStringArray() {
        final String[] obtained = sut.substringsBetween("", "jao", "maria");
        assertThat(obtained).isEmpty();
    }


}