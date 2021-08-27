package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class IntArrayTwoTimesIterator implements Iterator<Integer> {

    private final int[] array;
    private int index = 0;
    private int repeatCount = 0;

    public IntArrayTwoTimesIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int value = array[index];
        repeatCount++;
        if (repeatCount == 2) {
            index++;
            repeatCount = 0;
        }
        return value;
    }
}
