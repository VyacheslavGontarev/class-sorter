package test;

import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusBuilder;
import ru.autobus.model.AutobusComparator;
import ru.autobus.sorters.BaseSorter;
import ru.autobus.sorters.Sorter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseSorterTest {

    Sorter sorter = new BaseSorter();
    Comparator comparator = new AutobusComparator();

    @Test
    void testSort_correctOrder() {

        Autobus bus1 = new AutobusBuilder().withNumber(2).withModel("B").withMileage(5000).build();
        Autobus bus2 = new AutobusBuilder().withNumber(1).withModel("A").withMileage(2000).build();
        Autobus bus3 = new AutobusBuilder().withNumber(3).withModel("A").withMileage(1000).build();

        List<Autobus> buses = new ArrayList<>();
        buses.add(bus1);
        buses.add(bus2);
        buses.add(bus3);

        sorter.sort(buses, comparator);

        assertEquals(0, comparator.compare(buses.get(0), bus2));
        assertEquals(0, comparator.compare(buses.get(1), bus1));
        assertEquals(0, comparator.compare(buses.get(2), bus3));
    }

    @Test
    void testSort_emptyList() {
        List<Autobus> buses = new ArrayList<>();
        sorter.sort(buses, comparator);
        assertTrue(buses.isEmpty());
    }

    @Test
    void testSort_singleElement() {
        Autobus bus = new AutobusBuilder().withNumber(1).withModel("Model").withMileage(100).build();
        List<Autobus> buses = new ArrayList<>();
        buses.add(bus);

        sorter.sort(buses, comparator);
        assertEquals(1, buses.size());
        assertEquals(0, comparator.compare(buses.get(0), bus));
    }
}
