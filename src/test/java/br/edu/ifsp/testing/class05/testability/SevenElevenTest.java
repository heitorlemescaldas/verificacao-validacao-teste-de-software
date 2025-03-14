package br.edu.ifsp.testing.class05.testability;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SevenElevenTest {

    @Mock private Die die;
    @InjectMocks private SevenEleven sut;

    @Test
    @DisplayName("Should win if sum is equal to seven")
    void shouldWinIfSumIsEqualToSeven() {
        when(die.roll()).thenReturn(1).thenReturn(6);
        assertThat(sut.play()).isPositive();
    }
}