package ru.autobus.threads;

import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusBuilder;
import ru.autobus.validator.Validator;

import java.util.Scanner;

public class InputUtils {

    private static final Validator validator = new Validator();

    public static int readPositiveInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (validator.validateNumber(input)) {
                return Integer.parseInt(input);
            }
            System.out.print("Число не должно быть отрицательным. Повторите ввод: ");
        }
    }

    public static String readNonEmptyString(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (validator.validateModel(input)) {
                return input;
            }
            System.out.print("Строка не может быть пустой. Повторите ввод: ");
        }
    }

    public static Autobus readFromConsole(Scanner scanner) {
        System.out.print("Номер: ");
        int number = readPositiveInt(scanner);

        System.out.print("Модель: ");
        String model = readNonEmptyString(scanner);

        System.out.print("Пробег: ");
        int mileage = readPositiveInt(scanner);

        return new AutobusBuilder()
                .withNumber(number)
                .withModel(model)
                .withMileage(mileage)
                .build();
    }
}


