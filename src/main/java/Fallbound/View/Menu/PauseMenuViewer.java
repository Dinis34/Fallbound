package Fallbound.View.Menu;

import Fallbound.GUI.GUI;
import Fallbound.Model.Menu.PauseMenu;
import Fallbound.Model.Position;

public class PauseMenuViewer extends MenuViewer<PauseMenu> {

    public PauseMenuViewer(PauseMenu menu) {
        super(menu, new Position(4, 25));
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        drawOptions(gui);
        drawMenuTitle(gui, "⁜ PAUSE MENU ⁜", new Position(4, 24));
    }
}
