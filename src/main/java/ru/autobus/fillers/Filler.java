package ru.autobus.fillers;

import ru.autobus.model.Autobus;
import ru.autobus.model.MyArrayList;

public interface Filler {
    MyArrayList<Autobus> fill(int size);
}
