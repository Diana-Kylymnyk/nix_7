package csv.elements;

import java.util.ArrayList;
import java.util.List;

public final class Row {

    private final List<Cell> cells;

    public Row() {
        cells = new ArrayList<>();
    }

    public Row(List<String> cells) {
        this.cells = new ArrayList<>(cells.size());
        for (String cell : cells) {
            this.cells.add(new Cell(cell));
        }
    }

    public void append(String value) {
        cells.add(new Cell(value));
    }

    public int size() {
        return cells.size();
    }

    public boolean isEmpty() {
        return cells.isEmpty();
    }

    public List<Cell> asList() {
        return new ArrayList<>(cells);
    }

    public Cell get(int index) {
        return cells.get(index);
    }
}
