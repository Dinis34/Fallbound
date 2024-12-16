package Fallbound.View.Menu;

import Fallbound.GUI.GUI;
import Fallbound.Model.Menu.GameOverMenu;
import Fallbound.Model.Position;

public class GameOverMenuViewer extends MenuViewer<GameOverMenu> {
    public GameOverMenuViewer(GameOverMenu menu) {
        super(menu, new Position(4, 25));
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        drawOptions(gui);
        drawMenuTitle(gui, "GAME OVER", new Position(4, 24));
    }
}
