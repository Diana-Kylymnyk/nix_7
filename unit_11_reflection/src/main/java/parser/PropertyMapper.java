package parser;

import annotation.PropertyKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

public class PropertyMapper {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public <T> T map(Class<T> entityClass, Properties properties) {
        LOGGER_INFO.info("Parsing object");
        T object = null;
        try {
            object = Objects.requireNonNull(entityClass.getConstructor()).newInstance();
            for (Field field : object.getClass().getFields()) {

                if (field.isAnnotationPresent(PropertyKey.class)) {
                    PropertyKey propertyKey = field.getAnnotation(PropertyKey.class);
                    String getValueFromProperty = properties.getProperty(propertyKey.value());

                    if (field.getType().equals(Integer.TYPE)) {
                        field.set(object, Integer.parseInt(getValueFromProperty));
                    } else if (field.getType().equals(Long.TYPE)) {
                        field.set(object, Long.parseLong(getValueFromProperty));
                    } else if (field.getType().equals(Float.TYPE)) {
                        field.set(object, Float.parseFloat(getValueFromProperty));
                    } else if (field.getType().equals(Double.TYPE)) {
                        field.set(object, Double.parseDouble(getValueFromProperty));
                    } else if (field.getType().equals(Boolean.TYPE)) {
                        field.set(object, Boolean.parseBoolean(getValueFromProperty));
                    } else if (field.getType() == Date.class) {
                        try {
                            field.set(object, DATE_FORMAT.parse(getValueFromProperty));
                        } catch (ParseException e) {
                            LOGGER_ERROR.error("Error: " + e);
                        }
                    } else {
                        field.set(object, getValueFromProperty);
                    }
                }
            }
        } catch (IllegalAccessException | NumberFormatException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            LOGGER_ERROR.error("Error: " + e);
        }
        return object;
    }
}
