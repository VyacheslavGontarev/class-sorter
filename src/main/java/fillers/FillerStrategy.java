package main.java.fillers;

import main.java.model.Autobus;

import java.util.List;

public class FillerStrategy {
    private Filler filler;

    public void setFiller(Filler filler) {
        this.filler = filler;
    }

    public List<Autobus> executeFiller(int size) {
        return filler.fill(size);
    }
}
