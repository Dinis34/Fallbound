package Fallbound;

import Fallbound.GUI.GUI;
import Fallbound.GUI.LanternaGUI;
import Fallbound.State.State;
import Fallbound.State.GameState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    private final GUI gui;
    private State state;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(90, 30);
        this.state = State.getInstance();
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        Game game = new Game();
        game.startGame();
    }

    private void startGame() throws IOException {
        while (this.state.getCurrentState() != GameState.QUIT_GAME) {
            long startTime = System.currentTimeMillis();
            state.step(gui, this, startTime);
            KeyStroke key = gui.getNextAction();
            if (key != null) {
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == ('q'))
                    gui.close();
                if (key.getKeyType() == KeyType.EOF)
                    break;
            }
        }
    }
}
