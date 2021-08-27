package com.epam.rd.autocode.iterator;

import java.util.Iterator;

public class TableIterable implements Iterable<String> {
    private final String[] columns;
    private final int[] rows;

    public TableIterable(String[] columns, int[] rows) {
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public Iterator<String> iterator() {
        return new TableIterator(columns, rows);
    }
}