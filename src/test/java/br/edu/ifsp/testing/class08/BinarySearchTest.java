package br.edu.ifsp.testing.class08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class BinarySearchTest {

    @ParameterizedTest(name = "Should find element {0} in index {1}.")
    @CsvSource({"2,1", "9,4"})
    @DisplayName("Should find element if present.")
    void shouldFindElement(int element, int index) {
        int[] array = new int[]{1, 2, 5, 6, 9, 11};
        BinarySearch sut = new BinarySearch();
        final int foundIndex = sut.binarySearch(array, element);
        assertThat(foundIndex).isEqualTo(index);
    }

//    @Test
//    @DisplayName("Should return -1 if the element is bigger than the last array element")
//    void shouldReturnMinusOneIfTheElementIsBiggerThanTheLastArrayElement() {
//        int[] array = new int[]{1, 11};
//        BinarySearch sut = new BinarySearch();
//        final int foundIndex = sut.binarySearch(array, 13);
//        assertThat(foundIndex).isEqualTo(-1);
//    }

    @Test
    @DisplayName("Should find no element in null array")
    void shouldFindNoElementInNullArray() {
        BinarySearch sut = new BinarySearch();
        final int foundIndex = sut.binarySearch(null, 1);
        assertThat(foundIndex).isEqualTo(-1);
    }

    @Test
    @DisplayName("Should find no element in empty array")
    void shouldFindNoElementInEmptyArray() {
        BinarySearch sut = new BinarySearch();
        final int foundIndex = sut.binarySearch(new int[]{}, 1);
        assertThat(foundIndex).isEqualTo(-1);
    }

    @Test
    @DisplayName("Should return -1 if element is not present")
    void shouldReturnMinusOneIfElementIsNotPresent() {
        BinarySearch sut = new BinarySearch();
        final int foundIndex = sut.binarySearch(new int[]{2,3,4}, 1);
        assertThat(foundIndex).isEqualTo(-1);
    }
}