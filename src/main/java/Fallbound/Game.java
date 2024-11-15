package Fallbound;

import Fallbound.GUI.GUI;
import Fallbound.GUI.LanternaGUI;
import Fallbound.Model.Position;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    private final GUI gui;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(90, 30);
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Game game = new Game();
        game.startGame();
    }

    private void startGame() throws IOException {
        while (true) {
            gui.clear();
            gui.drawText(new Position(2, 1), "hello world", String.valueOf("#FFFFFF"));
            gui.refresh();
            KeyStroke key = gui.getNextAction();
            if (key != null) {
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q'))
                    gui.close();
            }
        }
    }
}
