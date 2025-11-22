package ru.autobus.model;

import java.util.Objects;

public class Autobus {
    protected Integer number;
    protected String model;
    protected Integer mileage;

    protected Autobus() {
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getMileage() {
        return mileage;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Autobus{" +
                "number=" + number +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Autobus autobus = (Autobus) o;
        return Objects.equals(number, autobus.number) && Objects.equals(model, autobus.model) && Objects.equals(mileage, autobus.mileage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, mileage);
    }
}