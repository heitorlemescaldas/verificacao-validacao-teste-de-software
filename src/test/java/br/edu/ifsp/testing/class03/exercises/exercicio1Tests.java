package br.edu.ifsp.testing.class03.exercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class exercicio1Tests {

    @Test
    @DisplayName("str é nulo\n")
    void testingT1() {
        final StringUtils sut = new StringUtils();
        final String str = null;
        final String open = "a";
        final String close = "z";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertNull(obtained);
    }

    @Test
    @DisplayName("str é vazio\n")
    void testingT2() {
        final StringUtils sut = new StringUtils();
        final String str = "";
        final String open = "a";
        final String close = "z";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertArrayEquals(new String[0], obtained);
    }

    @Test
    @DisplayName("open é nulo\n")
    void testingT3() {
        final StringUtils sut = new StringUtils();
        final String str = "Heitor";
        final String open = null;
        final String close = "z";

        assertThrows(NullPointerException.class, () -> sut.substringsBetween(str, open, close));
    }

    @Test
    @DisplayName("open é vazio\n")
    void testingT4() {
        final StringUtils sut = new StringUtils();
        final String str = "Heitor";
        final String open = "";
        final String close = "z";
        final String[] obtained = sut.substringsBetween(str, open, close);

       assertNull(obtained);
    }

    @Test
    @DisplayName("close é nulo\n")
    void testingT5() {
        final StringUtils sut = new StringUtils();
        final String str = "Heitor";
        final String open = "a";
        final String close = null;

        assertThrows(NullPointerException.class, () -> sut.substringsBetween(str, open, close));
    }


    @Test
    @DisplayName("close é vazop\n")
    void testingT6() {
        final StringUtils sut = new StringUtils();
        final String str = "Heitor";
        final String open = "a";
        final String close = "";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertNull(obtained);
    }

    @Test
    @DisplayName("str é igual a open e diferente de close\n")
    void testingT7() {
        final StringUtils sut = new StringUtils();
        final String str = "A";
        final String open = "A";
        final String close = "Z";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertNull(obtained);
    }

    @Test
    @DisplayName("str é igual a close e diferente de open\n")
    void testingT8() {
        final StringUtils sut = new StringUtils();
        final String str = "Z";
        final String open = "A";
        final String close = "Z";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertNull(obtained);
    }

    @Test
    @DisplayName("str é igual a open e close\n")
    void testingT9() {
        final StringUtils sut = new StringUtils();
        final String str = "A";
        final String open = "A";
        final String close = "A";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertNull(obtained);
    }

    @Test
    @DisplayName("str é diferente de open e close")
    void testingT10() {
        final StringUtils sut = new StringUtils();
        final String str = "x";
        final String open = "a";
        final String close = "z";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertNull(obtained);
    }

    @Test
    @DisplayName("str contém open, mas não close\n")
    void testingT1_1() {
        final StringUtils sut = new StringUtils();
        final String str = "xHeitor";
        final String open = "x";
        final String close = "z";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertNull(obtained);
    }

    @Test
    @DisplayName("str contém close, mas não open\n")
    void testingT1_2() {
        final StringUtils sut = new StringUtils();
        final String str = "xHeitor";
        final String open = "z";
        final String close = "x";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertNull(obtained);
    }

    @Test
    @DisplayName("str não contém open ou close\n")
    void testingT1_3() {
        final StringUtils sut = new StringUtils();
        final String str = "xHeitors";
        final String open = "a";
        final String close = "z";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertNull(obtained);
    }

    @Test
    @DisplayName("str contém open e close uma vez\n")
    void testingT1_4() {
        final StringUtils sut = new StringUtils();
        final String str = "xxaHeitorzxx";
        final String open = "a";
        final String close = "z";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertArrayEquals(new String[]{"Heitor"}, obtained);
    }

    @Test
    @DisplayName("str contém open, mas não close\n")
    void testingT1_6() {
        final StringUtils sut = new StringUtils();
        final String str = "xxaHeitorxxaMaria";
        final String open = "a";
        final String close = "z";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertNull(obtained);
    }

    @Test
    @DisplayName("str contém close, mas não open\n")
    void testingT1_7() {
        final StringUtils sut = new StringUtils();
        final String str = "xxHeitorxx";
        final String open = "a";
        final String close = "z";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertNull(obtained);
    }

    @Test
    @DisplayName("str não contém open ou close\n")
    void testingT1_8() {
        final StringUtils sut = new StringUtils();
        final String str = "xxHeitorxx";
        final String open = "b";
        final String close = "t";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertNull(obtained);
    }

    @Test
    @DisplayName("str contém open e close uma vez\n")
    void testingT1_9() {
        final StringUtils sut = new StringUtils();
        final String str = "xxbHeitorcxx";
        final String open = "b";
        final String close = "c";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertArrayEquals(new String[]{"Heitor"}, obtained);
    }

    @Test
    @DisplayName(" str contém open e close múltiplas vezes\n")
    void testingT1_10() {
        final StringUtils sut = new StringUtils();
        final String str = "xxaJoaozxxaMariaz";
        final String open = "a";
        final String close = "z";
        final String[] obtained = sut.substringsBetween(str, open, close);

        assertArrayEquals(new String[]{"Joao", "Maria"}, obtained);
    }
}