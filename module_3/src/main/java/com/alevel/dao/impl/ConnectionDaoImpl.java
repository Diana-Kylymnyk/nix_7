package com.alevel.dao.impl;

import com.alevel.Main;
import com.alevel.dao.ConnectionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDaoImpl implements ConnectionDao {

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private static Connection connection = null;

    public ConnectionDaoImpl(String username, String password) {
        Properties props = loadProperties();
        String url = props.getProperty("url");
        props.setProperty("user", username);
        props.setProperty("password", password);

        try {
            connection = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            LOGGER_ERROR.error("Error ", e);
            throw new RuntimeException(new SQLException(e));
        }
    }

    private Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = Main.class.getResourceAsStream("/jdbs.properties")) {
            properties.load(input);
        } catch (IOException e) {
            LOGGER_ERROR.error("Error ", e);
            throw new UncheckedIOException(e);
        }

        return properties;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}