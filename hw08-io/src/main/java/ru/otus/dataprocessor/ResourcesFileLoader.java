package ru.otus.dataprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import ru.otus.model.Measurement;

@SuppressWarnings({"java:S2147", "java:S112"})
public class ResourcesFileLoader implements Loader {
    private final String fileName;
    private final ObjectMapper mapper;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
        this.mapper = new ObjectMapper();
    }

    @Override
    public List<Measurement> load() {
        List<Measurement> measurements;
        File file = new File(fileName);
        try {
            measurements = mapper.readValue(file, new TypeReference<List<Measurement>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // читает файл, парсит и возвращает результат
        return measurements != null ? measurements : Collections.emptyList();
    }
}
