package ru.autobus.sorters;

import ru.autobus.model.Autobus;

import java.util.Comparator;
import java.util.List;

public class BaseSorter implements Sorter {

    @Override
    public void sort(List<Autobus> buses, Comparator<Autobus> comparator) {
        for (int i = 0; i < buses.size() - 1; i++) {
            for (int j = 0; j < buses.size() - i - 1; j++) {
                if (comparator.compare(buses.get(j), buses.get(j + 1)) > 0) {
                    Autobus temp = buses.get(j);
                    buses.set(j, buses.get(j + 1));
                    buses.set(j + 1, temp);
                }
            }
        }
    }
}