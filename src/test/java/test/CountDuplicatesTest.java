package test;

import org.junit.jupiter.api.Test;
import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusBuilder;
import ru.autobus.model.MyArrayList;
import ru.autobus.threads.CountDuplicates;


public class CountDuplicatesTest {
    @Test
    void testNormalCase() {
        MyArrayList<Autobus> list = new MyArrayList<>();
        list.add(new AutobusBuilder().withNumber(1).withModel("A").withMileage(100).build());
        list.add(new AutobusBuilder().withNumber(2).withModel("B").withMileage(200).build());
        list.add(new AutobusBuilder().withNumber(1).withModel("A").withMileage(100).build());
        list.add(new AutobusBuilder().withNumber(3).withModel("C").withMileage(300).build());
        list.add(new AutobusBuilder().withNumber(1).withModel("A").withMileage(100).build());

        Autobus target = new AutobusBuilder().withNumber(1).withModel("A").withMileage(100).build();
        long count = CountDuplicates.count(list, target);

        System.out.println("Тест 1: обычный случай");
        System.out.println("Ожидаемое: 3, Фактическое: " + count);
        System.out.println(count == 3 ? "Пройдено\n" : "Провалено\n");
    }

    @Test
    void testEmptyList() {
        MyArrayList<Autobus> list = new MyArrayList<>();
        Autobus target = new AutobusBuilder().withNumber(1).withModel("A").withMileage(100).build();
        long count = CountDuplicates.count(list, target);

        System.out.println("Тест 2: пустой список");
        System.out.println("Ожидаемое: 0, Фактическое: " + count);
        System.out.println(count == 0 ? "Пройдено\n" : "Провалено\n");
    }

    @Test
    void testNoMatches() {
        MyArrayList<Autobus> list = new MyArrayList<>();
        list.add(new AutobusBuilder().withNumber(2).withModel("B").withMileage(200).build());
        list.add(new AutobusBuilder().withNumber(3).withModel("C").withMileage(300).build());

        Autobus target = new AutobusBuilder().withNumber(1).withModel("A").withMileage(100).build();
        long count = CountDuplicates.count(list, target);

        System.out.println("Тест 3: без совпадений");
        System.out.println("Ожидаемое: 0, Фактическое: " + count);
        System.out.println(count == 0 ? "Пройдено\n" : "Провалено\n");
    }

    @Test
    void testAllMatch() {
        MyArrayList<Autobus> list = new MyArrayList<>();
        list.add(new AutobusBuilder().withNumber(1).withModel("A").withMileage(100).build());
        list.add(new AutobusBuilder().withNumber(1).withModel("A").withMileage(100).build());
        list.add(new AutobusBuilder().withNumber(1).withModel("A").withMileage(100).build());

        Autobus target = new AutobusBuilder().withNumber(1).withModel("A").withMileage(100).build();
        long count = CountDuplicates.count(list, target);

        System.out.println("Тест 4: все совпадают");
        System.out.println("Ожидаемое: 3, Фактическое: " + count);
        System.out.println(count == 3 ? "Пройдено\n" : "Провалено\n");
    }
}

