package br.edu.ifsp.testing.class05.piutter;

import br.edu.ifsp.testing.class05.piutter.stub.PiutterServiceStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilterTrendingTopicsTest {

    @Mock
    PiutterService piutterServiceMock;
    @InjectMocks
    FilterTrendingTopics sut;

    @Test
    @DisplayName("Should filter topics according to filter using stub")
    void shouldFilterTopicsAccordingToFilterContentUsingStub() {
        //Given
        var piutterService = new PiutterServiceStub(); // Injecting stub to isolate the test
        var sut = new FilterTrendingTopics(piutterService);
        //When
        final List<String> obtained = sut.filterByContent("Jav");
        //Then
        assertThat(obtained).contains("Java", "JavaScript");
    }

    @Test
    @DisplayName("Should filter topics according to filter using mockito")
    void shouldFilterTopicsAccordingToFilterContentUsingMockito() {
        PiutterService piutterService = mock(); // static method that creates the mock
        var sut = new FilterTrendingTopics(piutterService);
        //pre-program expectations
        final List<String> topics = List.of("Java", "JavaScript", "TypeScript");
        when(piutterService.fetchTrendingTopics()).thenReturn(topics);
        final List<String> obtained = sut.filterByContent("Jav");
        assertThat(obtained).contains("Java", "JavaScript");
    }

    @Test
    @DisplayName("Should return empty list if no topics are found")
    void shouldReturnEmptyListIfNoTopicsAreFound() {
        when(piutterServiceMock.fetchTrendingTopics()).thenReturn(null);
        assertThat(sut.filterByContent("Type")).isEmpty();
    }

    @Test
    @DisplayName("Should return elements, throw, and than return empty lists")
    void shouldReturnElementsThrowAndThanReturnEmptyLists() {
        when(piutterServiceMock.fetchTrendingTopics())
                .thenReturn(List.of("Mock", "Mockito", "Mosquito"))
                .thenThrow(new IllegalStateException())
                .thenReturn(Collections.emptyList());
        assertThat(sut.filterByContent("Mock")).hasSize(2);
        assertThatIllegalStateException().isThrownBy(() -> sut.filterByContent("text"));
        assertThat(sut.filterByContent("text")).isEmpty();
        assertThat(sut.filterByContent("text")).isEmpty(); // keep the last defined expectation
    }

    @Test
    @DisplayName("Should fail test due to unnecessary stubbing use")
    void shouldFailTestDueToUnnecessaryStubbingUse() {
        when(piutterServiceMock.fetchTrendingTopics()).thenReturn(List.of("Hello"));
        assertThatIllegalArgumentException().isThrownBy(() -> sut.filterByContent(""));
    }
}