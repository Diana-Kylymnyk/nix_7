package csv.parser;

import csv.elements.Table;

public interface CSVParser<T> {
    Table parse(T source) throws Exception;
}

