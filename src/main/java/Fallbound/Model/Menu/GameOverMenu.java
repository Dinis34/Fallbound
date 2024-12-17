package Fallbound.Model.Menu;

import java.util.Arrays;

public class GameOverMenu extends Menu {
    private boolean newHighScore;

    public GameOverMenu() {
        options = Arrays.asList("restart", "exit to main menu", "exit to desktop");
    }

    public boolean isSelectedRestart() {
        return isSelected(0);
    }

    public boolean isSelectedExit() {
        return isSelected(1);
    }

    public boolean isSelectedExitToDesktop() {
        return isSelected(2);
    }

    public boolean isNewHighScore() {
        return newHighScore;
    }

    public void setNewHighScore(boolean newHighScore) {
        this.newHighScore = newHighScore;
    }
}
