package csv.parser.impl;

import csv.elements.Row;
import csv.elements.Table;
import csv.exception.CSVException;
import csv.parser.CSVParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public final class CSVFileParser implements CSVParser<Path> {

    @Override
    public Table parse(Path source) throws CSVException {
        try(BufferedReader reader = Files.newBufferedReader(source)) {
            String header = reader.readLine();
            if (header == null) {
                throw new CSVException("Can't parse CSV, file is empty");
            }
            Table table = new Table(parseLine(header));
            String line;
            while ((line = reader.readLine()) != null) {
                table.addRow(parseLine(line));
            }
            return table;
        } catch (IOException e) {
            throw new CSVException(e);
        }
    }

    private Row parseLine(String line) {
        return new Row(Arrays.asList(line.split(",")));
    }
}
