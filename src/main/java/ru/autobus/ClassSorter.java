package ru.autobus;

import ru.autobus.fillers.FileFiller;
import ru.autobus.fillers.FillerStrategy;
import ru.autobus.fillers.ManualFiller;
import ru.autobus.fillers.RandomFiller;
import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusComparator;
import ru.autobus.sorters.BaseSorter;
import ru.autobus.sorters.Sorter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClassSorter {
    public static void main(String[] args) {
        List<Autobus> autobuses = new ArrayList<>();
        String path = "autobuses.txt";
        Scanner scanner = new Scanner(System.in);
        int size;
        FillerStrategy filler = new FillerStrategy();
        Sorter sorter = new BaseSorter();
        AutobusComparator comparator = new AutobusComparator();

        System.out.println("Введите размер массива: ");
        size = scanner.nextInt();
        scanner.nextLine();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Создать список автобусов из файла");
            System.out.println("2. Создать список автобусов рандомно");
            System.out.println("3. Создать список автобусов вручную");
            System.out.println("4. Выход");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    filler.setFiller(new FileFiller(path));
                    autobuses = filler.executeFiller(size);
                    sorter.sort(autobuses, comparator);
                    System.out.println(autobuses);
                    break;
                case "2":
                    filler.setFiller(new RandomFiller());
                    autobuses = filler.executeFiller(size);
                    sorter.sort(autobuses, comparator);
                    System.out.println(autobuses);
                    break;
                case "3":
                    filler.setFiller(new ManualFiller(scanner));
                    autobuses = filler.executeFiller(size);
                    sorter.sort(autobuses, comparator);
                    System.out.println(autobuses);
                    break;
                case "4":
                    System.out.println("Выход из программы...");
                    return;
                default:
                    System.out.println("Такой команды пока нет(");
            }

        }
    }
}
