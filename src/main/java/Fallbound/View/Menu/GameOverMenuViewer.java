package Fallbound.View.Menu;

import Fallbound.GUI.GUI;
import Fallbound.Model.Menu.GameOverMenu;
import Fallbound.Model.Position;
import static Fallbound.View.Theme.FALLBOUND_RED;

public class GameOverMenuViewer extends MenuViewer<GameOverMenu>{
    public GameOverMenuViewer(GameOverMenu menu) {
        super(menu, new Position(4, 25));
    }
    @Override
    protected void drawElements(GUI gui,long time){
        drawOptions(gui);
        drawMenuTitle(gui, "GAME OVER", FALLBOUND_RED, new Position(4, 24));
    }
}
