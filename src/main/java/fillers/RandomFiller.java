package main.java.fillers;

import main.java.model.Autobus;
import main.java.model.AutobusBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFiller implements Filler {
    public List<Autobus> fill(int size) {
        List<Autobus> buses = new ArrayList<>();
        int number;
        String model;
        int mileage;
        String[] models = {"Volvo", "Mercedes", "MAN", "Scania"};

        for (int i = 0; i < size; i++) {
            number = new Random().nextInt(1000);
            model = models[new Random().nextInt(models.length)];
            mileage = new Random().nextInt(500000);
            Autobus autobus = new AutobusBuilder()
                    .withNumber(number)
                    .withModel(model)
                    .withMileage(mileage)
                    .build();
            buses.add(autobus);
        }
        return buses;
    }
}
