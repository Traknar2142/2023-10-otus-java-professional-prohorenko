package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
@SuppressWarnings({"java:S1068", "java:S112", "java:S3011"})
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor,
                            EntitySQLMetaData entitySQLMetaData,
                            EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection,
                entitySQLMetaData.getSelectByIdSql(),
                List.of(id),
                resultSet -> {
                    try {
                        ArrayList<Object> args = new ArrayList<>();
                        while (resultSet.next()) {
                            List<Field> allFields = entityClassMetaData.getAllFields();
                            for (Field field : allFields) {
                                Object arg = resultSet
                                        .getObject(resultSet
                                                .findColumn(field.getName()), field.getType());
                                args.add(arg);
                            }
                        }
                        return entityClassMetaData.getConstructor().newInstance(args.toArray());
                    } catch (SQLException | InstantiationException | IllegalAccessException |
                             InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public List<T> findAll(Connection connection) {
        ArrayList<Object> args = new ArrayList<>();
        List<Field> fields = entityClassMetaData.getAllFields();
        List<T> objects = new ArrayList<>();
        var constructor = entityClassMetaData.getConstructor();
        dbExecutor.executeSelect(connection,
                entitySQLMetaData.getSelectAllSql(),
                Collections.emptyList(),
                resultSet -> {
                    try {
                        while (resultSet.next()) {
                            for (Field field : fields) {
                                args.add(resultSet.getObject(resultSet.findColumn(field.getName())));
                            }
                            objects.add(constructor.newInstance(args));
                        }
                    } catch (SQLException | InvocationTargetException | InstantiationException |
                             IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    return Collections.emptyList();
                });
        return objects;
    }

    @Override
    public long insert(Connection connection, T client) {
        List<Object> params = new ArrayList<>();
        List<Field> fieldsWithoutId = entityClassMetaData.getFieldsWithoutId();
        for (Field f : fieldsWithoutId) {
            try {
                f.setAccessible(true);
                params.add(f.get(client));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(), params);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    @Override
    public void update(Connection connection, T client) {
        List<Object> params = new ArrayList<>();
        List<Field> allFields = entityClassMetaData.getAllFields();
        try {
            for (Field f : allFields) {
                f.setAccessible(true);
                params.add(f.get(client));
            }
            Field idField = entityClassMetaData.getIdField();
            idField.setAccessible(true);
            params.add(idField.get(client));
            dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(), params);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}


