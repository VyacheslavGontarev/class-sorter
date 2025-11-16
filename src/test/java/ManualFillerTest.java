package test.java;


import main.java.fillers.ManualFiller;
import main.java.model.Autobus;
import main.java.model.AutobusBuilder;
import main.java.model.AutobusComparator;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManualFillerTest {
    Comparator comparator = new AutobusComparator();

    @Test
    void testFill_ValidInput() {
        String input = "10\nVolvo\n12345\n20\nMercedes\n23456\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        ManualFiller filler = new ManualFiller(scanner);
        List<Autobus> result = filler.fill(2);
        Autobus actual = result.get(0);
        Autobus expected = new AutobusBuilder().withNumber(10).withModel("Volvo").withMileage(12345).build();
        assertEquals(2, result.size());
        assertEquals(expected, actual);
        assertEquals(0, comparator.compare(expected, actual));
    }
}
