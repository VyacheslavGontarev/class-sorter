package test.java;

import main.java.fillers.RandomFiller;
import main.java.model.Autobus;
import main.java.model.AutobusComparator;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomFillerTest {
    Comparator comparator = new AutobusComparator();
    @Test
    void testFill_CorrectSize() {
        RandomFiller filler = new RandomFiller();
        int size = 5;
        List<Autobus> result = filler.fill(size);
        assertEquals(size, result.size());
        for (Autobus bus : result) {
            assertNotNull(bus);
            String str = bus.toString();
            assertTrue(str.contains("model='"), "toString должен содержать поле model");
            assertFalse(str.contains("model=''"), "Модель не должна быть пустой");
        }
    }
}
