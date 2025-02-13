package br.edu.ifsp.testing.class03;

import org.junit.jupiter.api.*;

public class MethodsLifecycleTest {

    @BeforeAll
    static void setUp() {
        System.out.println("@BeforeAll: Expensive test fixture goes here (e.g., database connection).");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("@AfterAll: Code to close and/or free resources of @BeforeAll goes here.");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach: Repetitive fixture code (e.g., common configuration of SUT instances).");
    }

    @AfterEach
    void afterEach() {
        System.out.println("@AfterEach: Code to close and/or free resources of @BeforeEach.");
    }

    @Test
    void someTest() {
        System.out.println("@Test: Some test case code goes here (1).");
    }

    @Test
    void someOtherTest() {
        System.out.println("@Test: Some test case code goes here (2).");
    }
}


