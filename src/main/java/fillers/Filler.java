package main.java.fillers;

import main.java.model.Autobus;

import java.util.List;

public interface Filler {
    List<Autobus> fill(int size);
}
