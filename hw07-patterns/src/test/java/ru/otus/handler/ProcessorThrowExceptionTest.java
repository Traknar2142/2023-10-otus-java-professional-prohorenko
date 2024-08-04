package ru.otus.handler;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;
import ru.otus.processor.ProcessorThrowException;
import ru.otus.provider.DateTimeProvider;

class ProcessorThrowExceptionTest {
    @Test
    @DisplayName("Должно быть брошено исключение на четную секунду")
    void evenExceptionTest() {
        DateTimeProvider dateTimeProviderMock = mock(DateTimeProvider.class);
        ProcessorThrowException processorThrowException = new ProcessorThrowException(dateTimeProviderMock);
        when(dateTimeProviderMock.getSecond()).thenReturn(2);

        Message message = new Message.Builder(1L).field8("field8").build();

        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> processorThrowException.process(message));
    }

    @Test
    @DisplayName("Процессор должен отработать без исключений в нечетную секунду")
    void eventWithoutExceptionTest() {
        DateTimeProvider dateTimeProviderMock = mock(DateTimeProvider.class);
        ProcessorThrowException processorThrowException = new ProcessorThrowException(dateTimeProviderMock);
        when(dateTimeProviderMock.getSecond()).thenReturn(1);

        Message message = new Message.Builder(1L).field8("field8").build();
        assertDoesNotThrow(() -> processorThrowException.process(message));
    }
}
