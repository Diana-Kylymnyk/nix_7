package csv.mapper;

import csv.elements.Table;

import java.util.List;

public interface CSVMapper {

    <T> List<T> map(Table table, Class<T> type) throws Exception;
}
