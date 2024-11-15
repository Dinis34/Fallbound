package Fallbound.View.Menu;

import Fallbound.GUI.GUI;
import Fallbound.Model.Menu.StartMenu;
import Fallbound.Model.Position;

public class StartMenuViewer extends MenuViewer<StartMenu> {

    private final String colorTitle = "#FFD700"; // gold

    public StartMenuViewer(StartMenu menu) {
        super(menu, new Position(4, 26));
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        drawOptions(gui);
        drawMenuTitle(gui, "START MENU", colorTitle, new Position(4, 24));
    }
}
