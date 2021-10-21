package com.alevel.service;

import com.alevel.dao.impl.ConnectionDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");

    private final ConnectionDaoImpl connectionDaoImpl;

    public ConnectionService(ConnectionDaoImpl dao) {
        connectionDaoImpl = dao;
    }

    public Connection getConnection() {
        LOGGER_INFO.info("Start connection");
        return connectionDaoImpl.getConnection();
    }

    public void close() throws SQLException {
        connectionDaoImpl.close();
    }
}
