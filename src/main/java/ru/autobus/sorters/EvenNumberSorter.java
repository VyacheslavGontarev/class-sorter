package ru.autobus.sorters;

import ru.autobus.model.Autobus;
import ru.autobus.model.MyArrayList;

import java.util.Comparator;

public class EvenNumberSorter implements Sorter {

    @Override
    public void sort(MyArrayList<Autobus> buses, Comparator<Autobus> comparator) {
        int n = buses.size();
        Autobus[] evenElements = new Autobus[n];
        int[] evenIndices = new int[n];
        int evenCount = 0;

        for (int i = 0; i < n; i++) {
            Autobus bus = buses.get(i);
            if (bus.getNumber() != null && bus.getNumber() % 2 == 0) {
                evenElements[evenCount] = bus;
                evenIndices[evenCount] = i;
                evenCount++;
            }
        }

        for (int i = 0; i < evenCount - 1; i++) {
            for (int j = 0; j < evenCount - i - 1; j++) {
                if (comparator.compare(evenElements[j], evenElements[j + 1]) > 0) {
                    Autobus temp = evenElements[j];
                    evenElements[j] = evenElements[j + 1];
                    evenElements[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < evenCount; i++) {
            buses.set(evenIndices[i], evenElements[i]);
        }
    }
}
