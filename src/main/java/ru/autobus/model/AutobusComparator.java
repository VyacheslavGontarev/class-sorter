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
                return b1.getNumber().compareTo(b2.getNumber());
            case MODEL:
                return b1.getModel().compareTo(b2.getModel());
            case MILEAGE:
                return Integer.compare(b1.getMileage(), b2.getMileage());
            default:
                throw new IllegalArgumentException("Unknown sort field: " + sortField);
        }
    }
}
