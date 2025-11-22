package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusBuilder;
import ru.autobus.model.AutobusComparator;
import ru.autobus.model.MyArrayList;
import ru.autobus.sorters.EvenMileageSorter;

import static org.junit.jupiter.api.Assertions.*;

public class EvenMileageSorterTest {
    private EvenMileageSorter sorter;
    private AutobusComparator comparator;
    private MyArrayList<Autobus> autobuses;

    @BeforeEach
    void setUp() {
        sorter = new EvenMileageSorter();
        comparator = new AutobusComparator(AutobusComparator.SortField.MILEAGE);
        autobuses = new MyArrayList<>();
    }

    @Test
    void testSortEvenMileageAscending() {
        // Given: Список автобусов с четным и нечетным пробегом (четные пробеги в произвольном порядке)
        autobuses.add(new AutobusBuilder().withNumber(1).withModel("Model A").withMileage(4000).build());
        autobuses.add(new AutobusBuilder().withNumber(2).withModel("Model B").withMileage(3001).build());
        autobuses.add(new AutobusBuilder().withNumber(3).withModel("Model C").withMileage(1000).build());
        autobuses.add(new AutobusBuilder().withNumber(4).withModel("Model D").withMileage(5001).build());
        autobuses.add(new AutobusBuilder().withNumber(5).withModel("Model E").withMileage(2000).build());

        // When: Сортировка по четному пробегу
        sorter.sort(autobuses, comparator);

        // Then: Четный пробег отсортирован по возрастанию, нечетный остается на месте
        assertEquals(3, autobuses.get(0).getNumber());
        assertEquals(2, autobuses.get(1).getNumber());
        assertEquals(5, autobuses.get(2).getNumber());
        assertEquals(4, autobuses.get(3).getNumber());
        assertEquals(1, autobuses.get(4).getNumber());
        
        // Проверяем значения пробега
        assertEquals(1000, autobuses.get(0).getMileage());
        assertEquals(3001, autobuses.get(1).getMileage());
        assertEquals(2000, autobuses.get(2).getMileage());
        assertEquals(5001, autobuses.get(3).getMileage());
        assertEquals(4000, autobuses.get(4).getMileage());
    }

    @Test
    void testSortWithAllEvenMileage() {
        // Given: Все автобусы имеют четный пробег
        autobuses.add(new AutobusBuilder().withNumber(1).withModel("Model A").withMileage(5000).build());
        autobuses.add(new AutobusBuilder().withNumber(2).withModel("Model B").withMileage(1000).build());
        autobuses.add(new AutobusBuilder().withNumber(3).withModel("Model C").withMileage(3000).build());

        // When: Сортировка
        sorter.sort(autobuses, comparator);

        // Then: Все элементы отсортированы по возрастанию пробега
        assertEquals(2, autobuses.get(0).getNumber());
        assertEquals(3, autobuses.get(1).getNumber());
        assertEquals(1, autobuses.get(2).getNumber());
        
        assertEquals(1000, autobuses.get(0).getMileage());
        assertEquals(3000, autobuses.get(1).getMileage());
        assertEquals(5000, autobuses.get(2).getMileage());
    }

    @Test
    void testSortWithAllOddMileage() {
        // Given: Все автобусы имеют нечетный пробег
        autobuses.add(new AutobusBuilder().withNumber(1).withModel("Model A").withMileage(1001).build());
        autobuses.add(new AutobusBuilder().withNumber(2).withModel("Model B").withMileage(3001).build());
        autobuses.add(new AutobusBuilder().withNumber(3).withModel("Model C").withMileage(5001).build());

        // When: Сортировка
        sorter.sort(autobuses, comparator);

        // Then: Порядок не изменился, так как нет четного пробега
        assertEquals(1, autobuses.get(0).getNumber());
        assertEquals(2, autobuses.get(1).getNumber());
        assertEquals(3, autobuses.get(2).getNumber());
        
        assertEquals(1001, autobuses.get(0).getMileage());
        assertEquals(3001, autobuses.get(1).getMileage());
        assertEquals(5001, autobuses.get(2).getMileage());
    }

    @Test
    void testSortEmptyList() {
        // Given: Пустой список
        // When: Сортировка пустого списка
        sorter.sort(autobuses, comparator);

        // Then: Список остается пустым
        assertTrue(autobuses.isEmpty());
    }

    @Test
    void testSortSingleEvenMileage() {
        // Given: Один автобус с четным пробегом
        autobuses.add(new AutobusBuilder().withNumber(1).withModel("Model A").withMileage(2000).build());

        // When: Сортировка
        sorter.sort(autobuses, comparator);

        // Then: Элемент остается на месте
        assertEquals(1, autobuses.get(0).getNumber());
        assertEquals(2000, autobuses.get(0).getMileage());
    }

    @Test
    void testSortSingleOddMileage() {
        // Given: Один автобус с нечетным пробегом
        autobuses.add(new AutobusBuilder().withNumber(1).withModel("Model A").withMileage(2001).build());

        // When: Сортировка
        sorter.sort(autobuses, comparator);

        // Then: Элемент остается на месте
        assertEquals(1, autobuses.get(0).getNumber());
        assertEquals(2001, autobuses.get(0).getMileage());
    }
}