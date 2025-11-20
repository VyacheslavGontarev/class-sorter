package ru.autobus.threads;

import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusBuilder;

import java.util.Scanner;

public class InputUtils {

    public static int readPositiveInt(Scanner scanner) {
        while (true) {
            try {
                String s = scanner.nextLine().trim();
                int n = Integer.parseInt(s);
                if (n >= 0) return n;

                System.out.print("Число не должно быть отрицательным. Повторите ввод: ");

            } catch (NumberFormatException e) {
                System.out.print("Введите корректное число: ");
            }
        }
    }

    public static String readNonEmptyString(Scanner scanner) {
        while (true) {
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) return s;

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


