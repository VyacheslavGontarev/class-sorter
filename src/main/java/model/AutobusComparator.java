package main.java.model;

import java.util.Comparator;

public class AutobusComparator implements Comparator<Autobus> {
    @Override
    public int compare(Autobus b1, Autobus b2) {
        int numberComp = b1.number.compareTo(b2.number);
        if (numberComp != 0) {
            return numberComp;
        }
        int modelComp = b1.model.compareTo(b2.model);
        if (modelComp != 0) {
            return modelComp;
        }
        return Integer.compare(b1.mileage, b2.mileage);
    }
}
