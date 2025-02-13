package br.edu.ifsp.testing.class04;

import org.junit.jupiter.api.*;

public class OrganizedNestedWithTest {

    @BeforeAll
    static void beforeAll(){System.out.println("BeforeAll: outer test class");}
    @AfterAll
    static void afterAll(){System.out.println("AfterAll: outer test class");}
    @BeforeEach
    void setUpOuter(){System.out.println("\tBeforeEach: outer test class");}
    @AfterEach
    void tearDownOuter(){System.out.println("\tAfterEach: outer test class");}

    @Nested
    @DisplayName("In method X")
    class SomeMethodTests { // ...
        @BeforeEach
        void setUpInner(){System.out.println("\t\tBeforeEach: inner test class");}
        @AfterEach
        void tearDownInner(){System.out.println("\t\tAfterEach: inner test class");}

        @Test
        void shouldTestSomething() {
            System.out.println("\t\t\tTesting method");
        }

        @Nested
        @DisplayName("should be able to")
        class TestValidClasses {
            @Test
            @DisplayName("do something with a and b")
            void shouldBeAbleToDoSomethingWithAAndB() {
            } //test something
        }

        @Nested
        @DisplayName("should NOT be able to")
        class TestInvalidClasses {
            @Test
            @DisplayName("do something else with a and b")
            void shouldBeAbleToDoSomethingElseWithAAndB() {
            } //test something else
        }


    }
}
