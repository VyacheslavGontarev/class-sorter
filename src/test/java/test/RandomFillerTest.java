package test;

import ru.autobus.fillers.RandomFiller;
import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusComparator;
import org.junit.jupiter.api.Test;
import ru.autobus.model.MyArrayList;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class RandomFillerTest {
    //Comparator comparator = new AutobusComparator(AutobusComparator.SortField.NUMBER);
    @Test
    void testFill_CorrectSize() {
        RandomFiller filler = new RandomFiller();
        int size = 5;
        MyArrayList<Autobus> result = filler.fill(size);
        assertEquals(size, result.size());
        for (int i = 0; i < result.size(); i++) {
            Autobus bus = result.get(i);
            assertNotNull(bus);
            String str = bus.toString();
            assertTrue(str.contains("model='"), "toString должен содержать поле model");
            assertFalse(str.contains("model=''"), "Модель не должна быть пустой");
        }
    }
}
