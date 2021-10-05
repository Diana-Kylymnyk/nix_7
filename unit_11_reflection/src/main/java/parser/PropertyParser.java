package parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyParser {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public Properties getProperty(String filePath) {

        LOGGER_INFO.info("Get properties");
        Properties property = new Properties();
        try (BufferedReader input = new BufferedReader(new FileReader(filePath))) {
            property.load(input);
        } catch (IOException e) {
            LOGGER_ERROR.error("Error: " + e);
        }
        return property;
    }
}