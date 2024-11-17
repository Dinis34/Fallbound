package Fallbound.GUI;

import Fallbound.Model.Position;

import java.io.IOException;
import java.util.Set;

public interface GUI {
    void drawElement(Position position, char character, String Color);

    void drawText(Position position, String text, String color);

    Set<Integer> getNextAction() throws IOException;

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    Set<Integer> getNextSingleAction();
}
