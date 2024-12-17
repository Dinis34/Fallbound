package Fallbound.Controller.Menu;

import Fallbound.Controller.Sound.SoundController;
import Fallbound.Game;
import Fallbound.Model.Menu.GameOverMenu;
import Fallbound.Model.Sound.SoundOption;
import Fallbound.State.GameState;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Set;

public class GameOverMenuController extends MenuController<GameOverMenu> {
    public static final String HIGHSCORE_FILE = "src/main/resources/highscore.txt";
    private final int currentScore;

    public GameOverMenuController(GameOverMenu menu, int currentScore) {
        super(menu);
        this.currentScore = currentScore;
        checkAndUpdateHighScore();
    }

    @Override
    public void step(Game game, Set<Integer> keys, long time) throws IOException {
        if (keys.contains(KeyEvent.VK_UP)) {
            getModel().previousOption();
        }
        if (keys.contains(KeyEvent.VK_DOWN)) {
            getModel().nextOption();
        }
        if (keys.contains(KeyEvent.VK_ENTER)) {
            if (getModel().isSelectedRestart()) {
                game.setState(GameState.NEW_GAME);
            } else if (getModel().isSelectedExit()) {
                game.setState(GameState.START_MENU);
                SoundController.getInstance().playSound(SoundOption.MENU_SELECT);
            } else if (getModel().isSelectedExitToDesktop()) {
                game.setState(GameState.QUIT_GAME);
            }
        }
    }

    private void checkAndUpdateHighScore() {
        boolean isHighScore = false;
        int highScore = loadHighScore();
        if (currentScore > highScore) {
            saveHighScore(currentScore);
            isHighScore = true;
        }
        getModel().setNewHighScore(isHighScore);
    }

    private void saveHighScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORE_FILE))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGHSCORE_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            } else {
                return 0;
            }
        } catch (IOException e) {
            return 0;
        }
    }
}