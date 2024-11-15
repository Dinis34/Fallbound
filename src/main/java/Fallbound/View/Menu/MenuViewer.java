package Fallbound.View.Menu;

import Fallbound.GUI.GUI;
import Fallbound.Model.Menu.Menu;
import Fallbound.Model.Position;
import Fallbound.View.Viewer;

public abstract class MenuViewer<T extends Menu> extends Viewer<T> {
    private final Position position;

    public MenuViewer(T menu, Position position) {
        super(menu);
        this.position = position;
    }

    protected void drawOptions(GUI gui) {
        int spacing = 1;
        for (int i = 0; i < getModel().getNumberOptions(); i++) {
            if (getModel().isSelected(i)) {
                gui.drawText(new Position(position.getX(), position.getY() + spacing * i), " " + getModel().getOption(i), "#FFFFFF");
            } else {
                gui.drawText(new Position(position.getX(), position.getY() + spacing * i), getModel().getOption(i), "#555555");
            }
        }
    }

    protected void drawMenuTitle(GUI gui, String title, String color, Position position) {
        gui.drawText(position, title, color);
    }
}
