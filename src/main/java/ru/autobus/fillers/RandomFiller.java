package ru.autobus.fillers;

import ru.autobus.model.Autobus;
import ru.autobus.model.AutobusBuilder;
import ru.autobus.model.MyArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomFiller implements Filler {
    public MyArrayList<Autobus> fill(int size) {
        String[] models = {"Volvo", "Mercedes", "MAN", "Scania"};
        Random random = new Random();
        MyArrayList<Autobus> buses = new MyArrayList<>();
        Stream.generate(() ->
                        new AutobusBuilder()
                                .withNumber(random.nextInt(1000))
                                .withModel(models[random.nextInt(models.length)])
                                .withMileage(random.nextInt(500000))
                                .build()
                )
                .limit(size)
                .forEach(buses::add);

        return buses;
    }
}
