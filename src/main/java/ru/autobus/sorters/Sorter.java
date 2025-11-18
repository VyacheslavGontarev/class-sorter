package ru.autobus.sorters;

import ru.autobus.model.Autobus;

import java.util.Comparator;
import java.util.List;

public interface Sorter {
    public void sort(List<Autobus> buses, Comparator<Autobus> comparator);
}
