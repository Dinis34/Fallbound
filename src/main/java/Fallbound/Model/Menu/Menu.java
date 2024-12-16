package Fallbound.Model.Menu;

import Fallbound.Controller.Sound.SoundController;
import Fallbound.Model.Sound.SoundOption;

import java.util.List;

public abstract class Menu {
    protected List<String> options;
    protected int selected = 0;

    public void nextOption() {
        selected++;
        if (selected >= options.size()) {
            selected = 0;
        }
        SoundController.getInstance().playSound(SoundOption.MENU_SELECT);
    }

    public void previousOption() {
        selected--;
        if (selected < 0) {
            selected = options.size() - 1;
        }
        SoundController.getInstance().playSound(SoundOption.MENU_SELECT);
    }

    public String getOption(int i) {
        return options.get(i);
    }

    public boolean isSelected(int i) {
        return selected == i;
    }

    public int getNumberOptions() {
        return options.size();
    }


}