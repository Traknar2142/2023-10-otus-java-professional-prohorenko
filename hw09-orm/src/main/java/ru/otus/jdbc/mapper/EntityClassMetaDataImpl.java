package ru.otus.jdbc.mapper;

import ru.otus.crm.annotation.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"java:S6204"})
public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private final Class<T> clazz;

    public EntityClassMetaDataImpl(T clazz) {
        this.clazz = (Class<T>) clazz;
    }

    @Override
    public String getName() {
        return clazz.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() {
        Constructor<?>[] constructors = clazz.getConstructors();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == declaredFields.length) {
                return (Constructor<T>) constructor;
            }
        }
        return null;
    }

    @Override
    public Field getIdField() {
        //По контракту возвращаем Field, по этому на Optional не стал переделывать
        return Arrays
                .stream(clazz.getDeclaredFields())
                .filter(m -> m.isAnnotationPresent(Id.class))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Field> getAllFields() {
        return List.of(clazz.getDeclaredFields());
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return Arrays
                .stream(clazz.getDeclaredFields())
                .filter(m -> !m.isAnnotationPresent(Id.class))
                .collect(Collectors.toList());
    }
}
