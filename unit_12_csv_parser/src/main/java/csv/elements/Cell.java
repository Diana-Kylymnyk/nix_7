package csv.elements;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public final class Cell {

    private final String value;

    public Cell(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

    public int getInt() {
        return Integer.parseInt(value);
    }

    public long getLong() {
        return Long.parseLong(value);
    }

    public double getDouble() {
        return Double.parseDouble(value);
    }

    public boolean getBoolean() {
        return Boolean.parseBoolean(value);
    }

    public LocalDate getLocalDate(DateTimeFormatter formatter) {
        return LocalDate.parse(value, formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell csvCell = (Cell) o;
        return Objects.equals(value, csvCell.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
