package ru.otus.provider;

import java.time.LocalDateTime;

public class DateTimeProvider {
    private final LocalDateTime localDateTime;

    public DateTimeProvider(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getSecond() {
        return localDateTime.getSecond();
    }
}
