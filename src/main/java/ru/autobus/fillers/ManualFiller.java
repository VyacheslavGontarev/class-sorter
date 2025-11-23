package ru.autobus.fillers;

import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusBuilder;
import ru.autobus.model.MyArrayList;
import ru.autobus.validator.Validator;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ManualFiller implements Filler {
    private final Scanner scanner;
    Validator validator = new Validator();

    public ManualFiller(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public MyArrayList<Autobus> fill(int size) {
        MyArrayList<Autobus> buses = new MyArrayList<>();
        System.out.println("Заполните данные для " + size + " автобусов.");

        IntStream.range(0, size).forEach(i -> {
            System.out.println("Автобус #" + (i + 1));

            String number = readUntilValid(
                    "Введите номер: ",
                    validator::validateNumber,
                    "Номер не может быть пустым или отрицательным. Повторите ввод."
            );

            String model = readUntilValid(
                    "Введите модель: ",
                    validator::validateModel,
                    "Модель не может быть пустой. Повторите ввод."
            );

            String mileage = readUntilValid(
                    "Введите пробег: ",
                    validator::validateMileage,
                    "Пробег не может быть пустым или отрицательным. Повторите ввод."
            );

            buses.add(new AutobusBuilder()
                    .withNumber(Integer.parseInt(number))
                    .withModel(model)
                    .withMileage(Integer.parseInt(mileage))
                    .build());
        });

        return buses;
    }

    private String readUntilValid(String prompt, Predicate<String> validator, String errorMessage) {
        return Stream.generate(() -> {
                    System.out.print(prompt);
                    String input = scanner.nextLine().trim();
                    if (!validator.test(input)) {
                        System.out.println(errorMessage);
                    }
                    return input;
                })
                .filter(validator)
                .findFirst()
                .get();
    }
}