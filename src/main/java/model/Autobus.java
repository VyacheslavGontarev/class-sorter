package main.java.model;

public class Autobus {
    protected Integer number;
    protected String model;
    protected Integer mileage;

    protected Autobus() {    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Autobus{" +
                "number=" + number +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}