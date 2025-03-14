package br.edu.ifsp.testing.class05.piutter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PublishPiueetTest {
    @Mock PiutterService piutterServiceMock;
    @InjectMocks PublishPiueet sut;
    final UUID userUuid = UUID.randomUUID();

    @Test
    @DisplayName("Should return false if service can not publish a piueet")
    void shouldReturnFalseIfServiceCanNotPublishAPiueetWithStub() {
        String msg = "In the middle of every difficulty lies opportunity. — Albert Einstein";
        when(piutterServiceMock.piueet(userUuid, msg)).thenReturn(false);
        final boolean obtained = sut.send(userUuid, msg);
        assertThat(obtained).isFalse();
    }

    @Test
    @DisplayName("Should return false if service can not publish a piueet")
    void shouldReturnFalseIfServiceCanNotPublishAPiueetMock() {
        String msg = "Stay hungry, stay foolish. — Steve Jobs";
        when(piutterServiceMock.piueet(userUuid, msg)).thenReturn(false);
        final boolean obtained = sut.send(userUuid, msg);
        verify(piutterServiceMock, times(1)).piueet(userUuid, msg);
        assertThat(obtained).isFalse();
    }
}