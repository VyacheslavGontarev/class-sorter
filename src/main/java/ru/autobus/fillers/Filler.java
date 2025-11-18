package ru.autobus.fillers;

import ru.autobus.model.Autobus;

import java.util.List;

public interface Filler {
    List<Autobus> fill(int size);
}
