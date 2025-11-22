package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusBuilder;
import ru.autobus.model.AutobusComparator;
import ru.autobus.model.MyArrayList;
import ru.autobus.sorters.EvenNumberSorter;

import static org.junit.jupiter.api.Assertions.*;

public class EvenNumberSorterTest {
    private EvenNumberSorter sorter;
    private AutobusComparator comparator;
    private MyArrayList<Autobus> autobuses;

    @BeforeEach
    void setUp() {
        sorter = new EvenNumberSorter();
        comparator = new AutobusComparator(AutobusComparator.SortField.NUMBER);
        autobuses = new MyArrayList<>();
    }

    @Test
    void testSortEvenNumbersAscending() {
        // Given: Список автобусов с четными и нечетными номерами
        autobuses.add(new AutobusBuilder().withNumber(10).withModel("Model A").withMileage(1000).build());
        autobuses.add(new AutobusBuilder().withNumber(5).withModel("Model B").withMileage(2000).build());
        autobuses.add(new AutobusBuilder().withNumber(2).withModel("Model C").withMileage(3000).build());
        autobuses.add(new AutobusBuilder().withNumber(7).withModel("Model D").withMileage(4000).build());
        autobuses.add(new AutobusBuilder().withNumber(8).withModel("Model E").withMileage(5000).build());

        // When: Сортировка по четным номерам
        sorter.sort(autobuses, comparator);

        // Then: Четные номера отсортированы по возрастанию, нечетные остались на своих местах
        assertEquals(2, autobuses.get(0).getNumber());
        assertEquals(5, autobuses.get(1).getNumber());
        assertEquals(8, autobuses.get(2).getNumber());
        assertEquals(7, autobuses.get(3).getNumber());
        assertEquals(10, autobuses.get(4).getNumber());
    }

    @Test
    void testSortWithAllEvenNumbers() {
        // Given: Все номера четные
        autobuses.add(new AutobusBuilder().withNumber(10).withModel("Model A").withMileage(1000).build());
        autobuses.add(new AutobusBuilder().withNumber(2).withModel("Model B").withMileage(2000).build());
        autobuses.add(new AutobusBuilder().withNumber(6).withModel("Model C").withMileage(3000).build());

        // When: Сортировка
        sorter.sort(autobuses, comparator);

        // Then: Все элементы отсортированы по возрастанию
        assertEquals(2, autobuses.get(0).getNumber());
        assertEquals(6, autobuses.get(1).getNumber());
        assertEquals(10, autobuses.get(2).getNumber());
    }

    @Test
    void testSortWithAllOddNumbers() {
        // Given: Все номера нечетные
        autobuses.add(new AutobusBuilder().withNumber(5).withModel("Model A").withMileage(1000).build());
        autobuses.add(new AutobusBuilder().withNumber(3).withModel("Model B").withMileage(2000).build());
        autobuses.add(new AutobusBuilder().withNumber(7).withModel("Model C").withMileage(3000).build());

        // When: Сортировка
        sorter.sort(autobuses, comparator);

        // Then: Порядок не изменился, так как нет четных номеров
        assertEquals(5, autobuses.get(0).getNumber());
        assertEquals(3, autobuses.get(1).getNumber());
        assertEquals(7, autobuses.get(2).getNumber());
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
    void testSortSingleEvenElement() {
        // Given: Один элемент с четным номером
        autobuses.add(new AutobusBuilder().withNumber(4).withModel("Model A").withMileage(1000).build());

        // When: Сортировка
        sorter.sort(autobuses, comparator);

        // Then: Элемент остается на месте
        assertEquals(4, autobuses.get(0).getNumber());
    }

    @Test
    void testSortSingleOddElement() {
        // Given: Один элемент с нечетным номером
        autobuses.add(new AutobusBuilder().withNumber(3).withModel("Model A").withMileage(1000).build());

        // When: Сортировка
        sorter.sort(autobuses, comparator);

        // Then: Элемент остается на месте
        assertEquals(3, autobuses.get(0).getNumber());
    }
}