package Fallbound.Model.Menu;

import java.util.Arrays;

public class GameOverMenu extends Menu {
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
}
