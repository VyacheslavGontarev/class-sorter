package ru.autobus.model;

public class AutobusBuilder {
    private Autobus newAutobus;

    public AutobusBuilder() {
        newAutobus = new Autobus();
    }

    public AutobusBuilder withNumber(int number) {
        newAutobus.number = number;
        return this;
    }

    public AutobusBuilder withModel(String model) {
        newAutobus.model = model;
        return this;
    }

    public AutobusBuilder withMileage (int mileage) {
        newAutobus.mileage = mileage;
        return this;
    }

    public Autobus build() {
        return newAutobus;
    }
}
