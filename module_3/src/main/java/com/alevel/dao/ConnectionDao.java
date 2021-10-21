package com.alevel.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionDao extends AutoCloseable {

    Connection getConnection();

    @Override
    void close() throws SQLException;
}
