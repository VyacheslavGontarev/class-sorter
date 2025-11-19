package ru.autobus.model;

import java.util.Comparator;

public class AutobusComparator implements Comparator<Autobus> {
    public enum SortField {
        NUMBER, MODEL, MILEAGE
    }
    
    private SortField sortField;
    
    public AutobusComparator(SortField sortField) {
        this.sortField = sortField;
    }
    
    @Override
    public int compare(Autobus b1, Autobus b2) {
        switch (sortField) {
            case NUMBER:
                return b1.number.compareTo(b2.number);
            case MODEL:
                return b1.model.compareTo(b2.model);
            case MILEAGE:
                return Integer.compare(b1.mileage, b2.mileage);
            default:
                throw new IllegalArgumentException("Unknown sort field: " + sortField);
        }
    }
}
