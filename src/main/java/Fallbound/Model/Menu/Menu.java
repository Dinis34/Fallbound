package Fallbound.Model.Menu;

import java.util.List;

public abstract class Menu {
    protected List<String> options;
    protected int selected = 0;

    public void nextOption() {
        selected++;
        if (selected >= options.size()) {
            selected = 0;
        }
    }

    public void previousOption() {
        selected--;
        if (selected < 0) {
            selected = options.size() - 1;
        }
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