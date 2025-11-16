package main.java.sorters;

import main.java.model.Autobus;

import java.util.Comparator;
import java.util.List;

public interface Sorter {
    public void sort(List<Autobus> buses, Comparator<Autobus> comparator);
}
