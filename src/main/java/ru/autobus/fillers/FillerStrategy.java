package ru.autobus.fillers;

import ru.autobus.model.Autobus;
import ru.autobus.model.MyArrayList;

public class FillerStrategy {
    private Filler filler;

    public void setFiller(Filler filler) {
        this.filler = filler;
    }

    public MyArrayList<Autobus> executeFiller(int size) {
        return filler.fill(size);
    }
}
