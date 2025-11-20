package test;


import ru.autobus.fillers.ManualFiller;
import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusBuilder;
import ru.autobus.model.AutobusComparator;
import org.junit.jupiter.api.*;
import ru.autobus.model.MyArrayList;

import java.io.ByteArrayInputStream;
import java.util.Comparator;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManualFillerTest {
    Comparator comparator = new AutobusComparator(AutobusComparator.SortField.MILEAGE);

    @Test
    void testFill_ValidInput() {
        String input = "10\nVolvo\n12345\n20\nMercedes\n23456\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        ManualFiller filler = new ManualFiller(scanner);
        MyArrayList<Autobus> result = filler.fill(2);
        Autobus actual = result.get(0);
        Autobus expected = new AutobusBuilder().withNumber(10).withModel("Volvo").withMileage(12345).build();
        assertEquals(2, result.size());
        assertEquals(expected, actual);
        assertEquals(0, comparator.compare(expected, actual));
    }
}
