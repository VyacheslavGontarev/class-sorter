package ru.autobus.fillers;

import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusBuilder;
import ru.autobus.model.MyArrayList;
import ru.autobus.validator.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class FileFiller implements Filler {
    String path;
    Validator validator = new Validator();

    public FileFiller(String path) {
        this.path = path;
    }

    @Override
    public MyArrayList<Autobus> fill(int size) {
        MyArrayList<Autobus> buses = new MyArrayList<>();
        try {
            Files.lines(Paths.get(path))
                    .limit(size)
                    .map(line -> line.split(","))
                    .filter(parts -> parts.length == 3)
                    .map(parts -> new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim()})
                    .filter(parts -> validator.validateNumber(parts[0]) &&
                            validator.validateModel(parts[1]) &&
                            validator.validateMileage(parts[2]))
                    .map(parts -> {
                        try {
                            return new AutobusBuilder()
                                    .withNumber(Integer.parseInt(parts[0]))
                                    .withModel(parts[1])
                                    .withMileage(Integer.parseInt(parts[2]))
                                    .build();
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .forEach(buses::add);

            return buses;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return new MyArrayList<>();
        }
    }
}