package br.edu.ifsp.testing.class07;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//{("error cases", 2), "cases error", 2), ("no case", 0)}

class RSWordCounterTest {
    
    @Test
    @DisplayName("Should cover part of all vertices")
    void shouldCoverPartOfAllVertices() {
        final RSWordCounter sut = new RSWordCounter();
        final int obtained = sut.count("error case");
        assertThat(obtained).isOne();
    }

//    @Test
//    @DisplayName("Should find word with r in the middle and s in the end")
//    void shouldFindWordWithRInTheMiddleAndSInTheEnd() {
//        final RSWordCounter sut = new RSWordCounter();
//        final int obtained = sut.count("error cases");
//        assertThat(obtained).isEqualTo(2);
//    }
//
//    @Test
//    @DisplayName("Should find word with s in the middle and r in the end")
//    void shouldFindWordWithSInTheMiddleAndRInTheEnd() {
//        final RSWordCounter sut = new RSWordCounter();
//        final int obtained = sut.count("cases error");
//        assertThat(obtained).isEqualTo(2);
//    }
//
//    @Test
//    @DisplayName("Should find no word")
//    void shouldFindNoWord() {
//        final RSWordCounter sut = new RSWordCounter();
//        final int obtained = sut.count("no case");
//        assertThat(obtained).isZero();
//    }
}