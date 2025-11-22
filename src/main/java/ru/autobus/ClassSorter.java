package ru.autobus;

import ru.autobus.fileutils.FileManager;
import ru.autobus.fillers.FileFiller;
import ru.autobus.fillers.FillerStrategy;
import ru.autobus.fillers.ManualFiller;
import ru.autobus.fillers.RandomFiller;
import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusComparator;
import ru.autobus.model.MyArrayList;
import ru.autobus.sorters.BaseSorter;
import ru.autobus.sorters.EvenMileageSorter;
import ru.autobus.sorters.EvenNumberSorter;
import ru.autobus.sorters.Sorter;
import ru.autobus.threads.InputUtils;
import ru.autobus.threads.CountDuplicates;

import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

public class ClassSorter {
    public static void main(String[] args) {
        MyArrayList<Autobus> autobuses = new MyArrayList<>();
        String path = "autobuses.txt";
        String outputPath = "output.txt";
        Scanner scanner = new Scanner(System.in);
        int size;
        FillerStrategy filler = new FillerStrategy();
        Sorter sorter = new BaseSorter();
        EvenNumberSorter evenNumberSorter = new EvenNumberSorter();
        EvenMileageSorter evenMileageSorter = new EvenMileageSorter();
        AutobusComparator comparator = new AutobusComparator(AutobusComparator.SortField.NUMBER); // default sorting by number

        FileManager <Autobus> fileManager = new FileManager<>(outputPath);
        if (fileManager.checkFileExists()){
            fileManager.clearFile();
        } else fileManager.createFile();

        System.out.println("Введите размер массива: ");
        size = scanner.nextInt();
        scanner.nextLine();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Создать список автобусов из файла");
            System.out.println("2. Создать список автобусов рандомно");
            System.out.println("3. Создать список автобусов вручную");
            System.out.println("4. Сортировать по номеру");
            System.out.println("5. Сортировать по модели");
            System.out.println("6. Сортировать по пробегу");
            System.out.println("7. Поиск одинаковых автобусов");
            System.out.println("8. Сортировать четные номера по порядку, нечетные остаются на месте");
            System.out.println("9. Сортировать автобусы с четным пробегом, нечетные остаются на месте");
            System.out.println("10. Выход");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    filler.setFiller(new FileFiller(path));
                    autobuses = filler.executeFiller(size);
                    System.out.println(autobuses);
                    fileManager.runAppendToFileDialog(scanner, autobuses);
                    break;
                case "2":
                    filler.setFiller(new RandomFiller());
                    autobuses = filler.executeFiller(size);
                    System.out.println(autobuses);
                    fileManager.runAppendToFileDialog(scanner, autobuses);
                    break;
                case "3":
                    filler.setFiller(new ManualFiller(scanner));
                    autobuses = filler.executeFiller(size);
                    System.out.println(autobuses);
                    fileManager.runAppendToFileDialog(scanner, autobuses);
                    break;
                case "4":
                    if (autobuses.isEmpty()) {
                        System.out.println("Сначала нужно сформировать список автобусов!");
                        break;
                    }
                    comparator = new AutobusComparator(AutobusComparator.SortField.NUMBER);
                    sorter.sort(autobuses, comparator);
                    System.out.println("Список отсортирован по номеру: " + autobuses);
                    fileManager.runAppendToFileDialog(scanner, autobuses);
                    break;
                case "5":
                    if (autobuses.isEmpty()) {
                        System.out.println("Сначала нужно сформировать список автобусов!");
                        break;
                    }
                    comparator = new AutobusComparator(AutobusComparator.SortField.MODEL);
                    sorter.sort(autobuses, comparator);
                    System.out.println("Список отсортирован по модели: " + autobuses);
                    fileManager.runAppendToFileDialog(scanner, autobuses);
                    break;
                case "6":
                    if (autobuses.isEmpty()) {
                        System.out.println("Сначала нужно сформировать список автобусов!");
                        break;
                    }
                    comparator = new AutobusComparator(AutobusComparator.SortField.MILEAGE);
                    sorter.sort(autobuses, comparator);
                    System.out.println("Список отсортирован по пробегу: " + autobuses);
                    fileManager.runAppendToFileDialog(scanner, autobuses);
                    break;
                case "7":
                    if (autobuses.isEmpty()) {
                        System.out.println("Сначала нужно сформировать список автобусов!");
                        break;
                    }

                    System.out.println("Введите данные автобуса, который нужно найти с помощью многопоточного поиска:");

                    Autobus target = InputUtils.readFromConsole(scanner);
                    long count = CountDuplicates.count(autobuses, target);

                    System.out.println("Искомый автобус: " + target);
                    System.out.println("Найдено одинаковых автобусов: " + count);
                    fileManager.runAppendToFileDialog(scanner, target, count);
                    break;
                case "8":
                    if (autobuses.isEmpty()) {
                        System.out.println("Сначала нужно сформировать список автобусов!");
                        break;
                    }
                    comparator = new AutobusComparator(AutobusComparator.SortField.NUMBER);
                    evenNumberSorter.sort(autobuses, comparator);
                    System.out.println("Список отсортирован по четным номерам: " + autobuses);
                    fileManager.runAppendToFileDialog(scanner, autobuses);
                    break;
                case "9":
                    if (autobuses.isEmpty()) {
                        System.out.println("Сначала нужно сформировать список автобусов!");
                        break;
                    }
                    comparator = new AutobusComparator(AutobusComparator.SortField.MILEAGE);
                    evenMileageSorter.sort(autobuses, comparator);
                    System.out.println("Список отсортирован по четному пробегу: " + autobuses);
                    fileManager.runAppendToFileDialog(scanner, autobuses);
                    break;
                case "10":
                    System.out.println("Выход из программы...");
                    return;
                default:
                    System.out.println("Такой команды пока нет(");
            }
        }
    }
}
