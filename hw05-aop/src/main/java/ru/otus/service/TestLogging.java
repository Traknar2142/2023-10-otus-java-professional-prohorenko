package ru.otus.service;

import ru.otus.annotation.Log;

@SuppressWarnings({"java:S117", "java:S1186"})
public class TestLogging implements TestLoggingInterface {

    @Override
    @Log
    public void calculation(int param_1) {}

    @Override
    @Log
    public void calculation(int param_1, int param_2) {}

    @Override
    public void calculation(int param_1, int param_2, int param_3) {}
}
