package ru.autobus.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;
public class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    private void sizeControl() {
        if (size >= 0.75 * elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }
    public boolean add(T element) {
        sizeControl();
        elements[size++] = element;
        return true;
    }
    public T get(int index) {
        try {
            if (checkIndex(index))
                return (T) elements[index];
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean addAll(Collection<? extends T> collection) {
        for (T element : collection)
            add(element);
        return true;
    }
    public T remove(int index) {
        checkIndex(index);
        T removed = get(index);
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return removed;
    }
    private boolean checkIndex(int index) {
        if (index < 0 || index >= size)
            return false;
        else return true;
    }
    @Override
    public String toString() {
        Object[] temp = new Object[size];
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }
        return Arrays.toString(temp);
    }
    public Stream<T> stream() {
        return (Stream<T>) Arrays.stream(elements, 0, size);
    }

    public int size() {
        return size;
    }

    public T set(int index, T element) {
        if (!checkIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T oldValue = (T) elements[index];
        elements[index] = element;
        return oldValue;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}