package ru.autobus.sorters;

import ru.autobus.model.Autobus;
import ru.autobus.model.MyArrayList;

import java.util.Comparator;

public interface Sorter {
    void sort(MyArrayList<Autobus> buses, Comparator<Autobus> comparator);
}
