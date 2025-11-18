package ru.autobus.sorters.special;

import ru.autobus.model.Autobus;
import ru.autobus.sorters.Sorter;

import java.util.Comparator;
import java.util.List;

public class EvenMileageSorter implements Sorter {
    @Override
    public void sort(List<Autobus> buses, Comparator<Autobus> comparator) {
        // Сортировка только автобусов с четным пробегом, нечетные остаются на своих местах
        for (int i = 0; i < buses.size() - 1; i++) {
            for (int j = 0; j < buses.size() - i - 1; j++) {
                Autobus bus1 = buses.get(j);
                Autobus bus2 = buses.get(j + 1);
                
                // Пропускаем сравнение, если один из автобусов имеет нечетный пробег
                if (bus1.getMileage() % 2 != 0 || bus2.getMileage() % 2 != 0) {
                    continue;
                }
                
                if (comparator.compare(bus1, bus2) > 0) {
                    Autobus temp = buses.get(j);
                    buses.set(j, buses.get(j + 1));
                    buses.set(j + 1, temp);
                }
            }
        }
    }
}
