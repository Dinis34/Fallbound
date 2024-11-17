package Fallbound.Model.Menu;

import java.util.Arrays;

public class PauseMenu extends Menu {
    public PauseMenu() {
        options = Arrays.asList("continue", "restart", "exit to main menu", "exit to desktop");
    }

    public boolean isSelectedContinue() {
        return isSelected(0);
    }

    public boolean isSelectedRestart() {
        return isSelected(1);
    }

    public boolean isSelectedExit() {
        return isSelected(2);
    }

    public boolean isSelectedExitToDesktop() {
        return isSelected(3);
    }
}
