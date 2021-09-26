package csv.mapper.impl;

import csv.elements.Cell;
import csv.elements.Row;
import csv.elements.Table;
import csv.exception.CSVException;
import csv.mapper.CSVMapper;
import csv.mapper.CSVMapping;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CSVAnnotationMapper implements CSVMapper {

    @Override
    public <T> List<T> map(Table table, Class<T> type) throws CSVException {
        try {
            List<Row> rows = table.getRows();
            List<T> result = new ArrayList<>(rows.size());
            Constructor<T> constructor = type.getConstructor();
            Field[] declaredFields = type.getDeclaredFields();
            Map<String, Field> columnToFieldMap = new HashMap<>(declaredFields.length);
            for (Field declaredField : declaredFields) {
                CSVMapping column = declaredField.getAnnotation(CSVMapping.class);
                if (column != null && declaredField.trySetAccessible()) {
                    columnToFieldMap.put(column.value(), declaredField);
                }
            }

            for (Row row : rows) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                T instance = constructor.newInstance();
                for (Map.Entry<String, Field> e : columnToFieldMap.entrySet()) {
                    String columnName = e.getKey();
                    Field field = e.getValue();
                    int index = table.getColumnIndex(columnName);
                    Cell cell = row.get(index);
                    Class<?> fieldType = field.getType();
                    if (fieldType.equals(String.class)) {
                        field.set(instance, cell.get());
                    } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                        field.setInt(instance, cell.getInt());
                    } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                        field.setBoolean(instance, cell.getBoolean());
                    } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                        field.setDouble(instance, cell.getDouble());
                    } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                        field.setLong(instance, cell.getLong());
                    } else if (fieldType.equals(LocalDate.class)) {
                        field.set(instance, cell.getLocalDate(formatter));
                    } else if (fieldType.isEnum()) {
                        Object[] constants = fieldType.getEnumConstants();
                        for (Object constant : constants) {
                            if (String.valueOf(constant).equalsIgnoreCase(cell.get())) {
                                field.set(instance, constant);
                                break;
                            }
                        }
                    }
                }
                result.add(instance);
            }
            return result;
        } catch (Exception e) {
            throw new CSVException(e);
        }

    }
}
