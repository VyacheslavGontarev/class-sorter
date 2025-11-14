package main.java.fillers;

import main.java.model.Autobus;
import main.java.model.AutobusBuilder;
import main.java.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualFiller implements Filler {
    private final Scanner scanner;
    Validator validator = new Validator();

    public ManualFiller(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public List<Autobus> fill(int size) {
        List<Autobus> buses = new ArrayList<>();
        System.out.println("Заполните данные для " + size + " автобусов.");

        for (int i = 0; i < size; i++) {
            System.out.println("Автобус #" + (i + 1));

        String number;
        do {
            System.out.print("Введите номер: ");
            number = scanner.nextLine().trim();
            if (!validator.validateNumber(number)) {
                System.out.println("Номер не может быть пустым или не положительным. Повторите ввод.");
            }
        } while (!validator.validateNumber(number));
        String model;
        do {
            System.out.print("Введите модель: ");
            model = scanner.nextLine().trim();
            if (!validator.validateModel(model)) {
                System.out.println("Модель не может быть пустой. Повторите ввод.");
            }
        } while (!validator.validateModel(model));

        String mileage;
        do {
            System.out.print("Введите пробег: ");
            mileage = scanner.nextLine().trim();
                if (!validator.validateMileage(mileage)) {
                    System.out.println("Пробег не может быть пустым или не положительным. Повторите ввод.");
                }
        } while (!validator.validateMileage(mileage));

        Autobus autobus = new AutobusBuilder()
                .withNumber(Integer.parseInt(number))
                .withModel(model)
                .withMileage(Integer.parseInt(mileage))
                .build();

        buses.add(autobus);
    }
        return buses;
}
}