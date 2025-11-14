package main.java.fillers;

import main.java.model.Autobus;
import main.java.model.AutobusBuilder;
import main.java.validator.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileFiller implements Filler {
    String path;
    Validator validator = new Validator();

    public FileFiller(String path) {
        this.path = path;
    }

    @Override
    public List<Autobus> fill(int size) {
        List<Autobus> buses = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                if (buses.size() >= size) {
                    break;
                }
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.out.println("Неверный формат строки: " + line);
                    continue;
                }
                String numberStr = parts[0].trim();
                String model = parts[1].trim();
                String mileageStr = parts[2].trim();

                if (!validator.validateNumber(numberStr) || !validator.validateModel(model)
                        || !validator.validateMileage(mileageStr)) {
                    System.out.println("Ошибка валидации данных: " + line);
                    continue;
                }

                int number = Integer.parseInt(numberStr);
                int mileage = Integer.parseInt(mileageStr);
                Autobus autobus = new AutobusBuilder()
                        .withNumber(number)
                        .withModel(model)
                        .withMileage(mileage)
                        .build();
                buses.add(autobus);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return buses;
    }
}
