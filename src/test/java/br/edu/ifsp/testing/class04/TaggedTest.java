package br.edu.ifsp.testing.class04;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TaggedTest {
    @Test
    @Tag("UnitTest")
    void shouldDoSomeUnitTestingX() {
        assertThat("").isEmpty();
    }

    @Test
    @Tag("UnitTest")
    void shouldDoSomeUnitTestingY() {
        assertThat(" ").isBlank();
    }

    @Test
    @Tag("IntegrationTest")
    void shouldDoSomeIntegrationTestingZ() {
        //some time-consuming integration test
    }
}


