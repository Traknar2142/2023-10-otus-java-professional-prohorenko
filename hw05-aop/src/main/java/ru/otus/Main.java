package ru.otus;

import ru.otus.ioc.Ioc;
import ru.otus.service.TestLoggingInterface;

public class Main {
    public static void main(String[] args) {
        TestLoggingInterface testLogging = Ioc.createTestLogging();
        testLogging.calculation(1);
        testLogging.calculation(1, 2);
        testLogging.calculation(1, 2, 3);
    }
}
