package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@SuppressWarnings({"java:S112"})
public class FileSerializer implements Serializer {
    private final String fileName;
    private ObjectMapper mapper;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
        this.mapper = new ObjectMapper();
    }

    @Override
    public void serialize(Map<String, Double> data) {
        File file = new File(fileName);
        try {
            mapper.writeValue(file, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
