package com.alevel.dao;

import com.alevel.entity.Accounts;

import java.time.Instant;

public interface ExportDataDao {

    void exportOperationsInPeriodToCsv(Accounts account, Instant dateFrom, Instant dateTo, String filePath);
}
