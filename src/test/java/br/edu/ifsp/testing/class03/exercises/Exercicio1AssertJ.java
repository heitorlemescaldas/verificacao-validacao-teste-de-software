package br.edu.ifsp.testing.class03.exercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class Exercicio1AssertJ {

    @Test
    @DisplayName("str é nulo\n")
    void testingT1() {
        final StringUtils sut = new StringUtils();
        final String str = null;
        final String open = "a";
        final String close = "z";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("str é vazio\n")
    void testingT2() {
        final StringUtils sut = new StringUtils();
        final String str = "";
        final String open = "a";
        final String close = "z";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isEmpty();
    }

    @Test
    @DisplayName("open é nulo\n")
    void testingT3() {
        final StringUtils sut = new StringUtils();
        final String str = "Heitor";
        final String open = null;
        final String close = "z";

        assertThatThrownBy(() -> sut.substringsBetween(str, open, close))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("open é vazio\n")
    void testingT4() {
        final StringUtils sut = new StringUtils();
        final String str = "Heitor";
        final String open = "";
        final String close = "z";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("close é nulo\n")
    void testingT5() {
        final StringUtils sut = new StringUtils();
        final String str = "Heitor";
        final String open = "a";
        final String close = null;

        assertThatThrownBy(() -> sut.substringsBetween(str, open, close))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("close é vazio\n")
    void testingT6() {
        final StringUtils sut = new StringUtils();
        final String str = "Heitor";
        final String open = "a";
        final String close = "";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("str é igual a open e diferente de close\n")
    void testingT7() {
        final StringUtils sut = new StringUtils();
        final String str = "A";
        final String open = "A";
        final String close = "Z";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("str é igual a close e diferente de open\n")
    void testingT8() {
        final StringUtils sut = new StringUtils();
        final String str = "Z";
        final String open = "A";
        final String close = "Z";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("str é igual a open e close\n")
    void testingT9() {
        final StringUtils sut = new StringUtils();
        final String str = "A";
        final String open = "A";
        final String close = "A";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("str é diferente de open e close")
    void testingT10() {
        final StringUtils sut = new StringUtils();
        final String str = "x";
        final String open = "a";
        final String close = "z";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("str contém open, mas não close\n")
    void testingT11() {
        final StringUtils sut = new StringUtils();
        final String str = "xHeitor";
        final String open = "x";
        final String close = "z";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("str contém close, mas não open\n")
    void testingT12() {
        final StringUtils sut = new StringUtils();
        final String str = "xHeitor";
        final String open = "z";
        final String close = "x";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("str não contém open ou close\n")
    void testingT13() {
        final StringUtils sut = new StringUtils();
        final String str = "xHeitors";
        final String open = "a";
        final String close = "z";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("str contém open e close uma vez\n")
    void testingT14() {
        final StringUtils sut = new StringUtils();
        final String str = "xxaHeitorzxx";
        final String open = "a";
        final String close = "z";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).containsExactly("Heitor");
    }

    @Test
    @DisplayName("str contém open, mas não close\n")
    void testingT15() {
        final StringUtils sut = new StringUtils();
        final String str = "xxaHeitorxxaMaria";
        final String open = "a";
        final String close = "z";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("str contém close, mas não open\n")
    void testingT16() {
        final StringUtils sut = new StringUtils();
        final String str = "xxHeitorxx";
        final String open = "a";
        final String close = "z";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("str não contém open ou close\n")
    void testingT17() {
        final StringUtils sut = new StringUtils();
        final String str = "xxHeitorxx";
        final String open = "b";
        final String close = "t";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).isNull();
    }

    @Test
    @DisplayName("str contém open e close uma vez\n")
    void testingT18() {
        final StringUtils sut = new StringUtils();
        final String str = "xxbHeitorcxx";
        final String open = "b";
        final String close = "c";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).containsExactly("Heitor");
    }

    @Test
    @DisplayName("str contém open e close múltiplas vezes\n")
    void testingT19() {
        final StringUtils sut = new StringUtils();
        final String str = "xxaJoaozxxaMariaz";
        final String open = "a";
        final String close = "z";

        final String[] obtained = sut.substringsBetween(str, open, close);

        assertThat(obtained).containsExactly("Joao", "Maria");
    }

    @Test
    @DisplayName("str contém open e close uma vez, mas nada entre eles\n")
    void TestingT20() {
        final StringUtils sut = new StringUtils();
        final String str = "az";
        final String open = "a";
        final String close = "z";

        final String[] obtained = sut.substringsBetween(str, open, close);
        assertThat(obtained).containsExactly("");
    }

    @Test
    @DisplayName("str contém espaços em branco e possui open e close múltiplas vezes\n")
    void TestingT21() {
        final StringUtils sut = new StringUtils();
        final String str = "a z a z a z";
        final String open = "a";
        final String close = "z";

        final String[] obtained = sut.substringsBetween(str, open, close);
        assertThat(obtained).containsExactly(" ", " ", " ");
    }

    @Test
    @DisplayName("str possui open e close múltiplas vezes; e as tags possuem espaços")
    void testingT22() {
        final StringUtils sut = new StringUtils();
        final String str = " <tag>Heitor</tag> <tag>Maria</tag> ";
        final String open = "<tag>";
        final String close = "</tag> ";

        final String[] obtained = sut.substringsBetween(str, open, close);

        // Espera capturar as duas substrings mesmo com espaços nas tags
        assertThat(obtained).containsExactly("Heitor", "Maria");
    }
}
