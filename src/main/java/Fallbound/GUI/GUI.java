package Fallbound.GUI;

import Fallbound.Model.Position;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public interface GUI {
    void drawElement(Position position, char character, String Color);

    void drawText(Position position, String text, String color);

    KeyStroke getNextAction() throws IOException;

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;
}
