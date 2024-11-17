package Fallbound.Controller;

import Fallbound.Game;

import java.io.IOException;
import java.util.Set;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Game game, Set<Integer> keys, long time) throws IOException;
}
