package ru.autobus.sorters;

import ru.autobus.model.Autobus;
import ru.autobus.model.MyArrayList;

import java.util.Comparator;

public class EvenMileageSorter implements Sorter {

    @Override
    public void sort(MyArrayList<Autobus> buses, Comparator<Autobus> comparator) {
        int n = buses.size();
        Autobus[] evenMileageElements = new Autobus[n];
        int[] evenMileageIndices = new int[n];
        int evenCount = 0;

        for (int i = 0; i < n; i++) {
            Autobus bus = buses.get(i);
            if (bus.getMileage() != null && bus.getMileage() % 2 == 0) {
                evenMileageElements[evenCount] = bus;
                evenMileageIndices[evenCount] = i;
                evenCount++;
            }
        }

        for (int i = 0; i < evenCount - 1; i++) {
            for (int j = 0; j < evenCount - i - 1; j++) {
                if (comparator.compare(evenMileageElements[j], evenMileageElements[j + 1]) > 0) {
                    Autobus temp = evenMileageElements[j];
                    evenMileageElements[j] = evenMileageElements[j + 1];
                    evenMileageElements[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < evenCount; i++) {
            buses.set(evenMileageIndices[i], evenMileageElements[i]);
        }
    }
}
