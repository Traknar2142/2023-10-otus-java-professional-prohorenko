package ru.otus.processor;

import ru.otus.model.Message;
import ru.otus.provider.DateTimeProvider;

@SuppressWarnings({"java:S112", "java:S2166"})
public class ProcessorThrowException implements Processor {
    private DateTimeProvider dateTimeProvider;

    public ProcessorThrowException(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        if (dateTimeProvider.getSecond() % 2 == 0) {
            throw new RuntimeException("Процессор вызван на четной секунде");
        }
        return message;
    }
}
