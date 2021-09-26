package csv.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Table {

    private final Map<String, Integer> header;

    private final List<Row> rows;

    public Table(Row header) {
        int columns = header.size();
        this.header = new HashMap<>(columns);
        for (int i = 0; i < columns; i++) {
            this.header.put(header.get(i).get(), i);
        }
        this.rows = new ArrayList<>();
    }

    public Row getRow(int index) {
        return rows.get(index);
    }

    public List<Row> getRows() {
        return rows;
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    public Cell getCell(int row, String column) {
        int index = header.get(column);
        return rows.get(row).get(index);
    }

    public int getColumnIndex(String column) {
        return header.get(column);
    }
}
