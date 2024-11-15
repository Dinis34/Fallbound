package Fallbound.Model.Menu;

import java.util.Arrays;

public class StartMenu extends Menu {
    public StartMenu() {
        options = Arrays.asList("Play", "Exit");
    }

    public boolean isSelectedPlay() {
        return isSelected(0);
    }

    public boolean isSelectedExit() {
        return isSelected(1);
    }
}
