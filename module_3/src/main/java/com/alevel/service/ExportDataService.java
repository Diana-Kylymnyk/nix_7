package com.alevel.service;

import com.alevel.dao.impl.ExportDataDaoImpl;
import com.alevel.entity.Accounts;
import com.alevel.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ExportDataService {
    private final ExportDataDaoImpl exportDataToCsvDao;
    private final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public ExportDataService(Connection connection) {
        this.exportDataToCsvDao = new ExportDataDaoImpl(connection);
    }

    public void exportOperationsInPeriodToCsv(User user, Accounts account, LocalDateTime dateFrom, LocalDateTime dateTo, String filePath) {
        if (!user.getId().equals(account.getUser().getId())) {
            LOGGER_ERROR.error("Error with export operations!" +
                    " Account with id {} doesn't belong to user with id {}.", account.getId(), user.getId());
            throw new RuntimeException(new IllegalAccessException("Current account doesn't belong to user!"));
        }
        exportDataToCsvDao.exportOperationsInPeriodToCsv(account,
                dateFrom.toInstant(ZoneOffset.UTC),
                dateTo.toInstant(ZoneOffset.UTC),
                filePath);
    }
}
