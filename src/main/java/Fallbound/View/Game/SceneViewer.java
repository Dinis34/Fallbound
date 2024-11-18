package Fallbound.View.Game;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Tiles.Wall;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Position;
import Fallbound.View.Game.Elements.CoinViewer;
import Fallbound.View.Game.Elements.PlayerViewer;
import Fallbound.View.Game.Elements.WallViewer;
import Fallbound.View.Viewer;

import static Fallbound.View.Theme.*;

public class SceneViewer extends Viewer<Scene> {

    private final WallViewer wallViewer = new WallViewer();
    private final PlayerViewer playerViewer = new PlayerViewer();
    private final CoinViewer coinViewer = new CoinViewer();

    public SceneViewer(Scene model) {
        super(model);
    }

    protected void drawHud(GUI gui) {
        gui.drawText(new Position(2, 1), "TIME", FALLBOUND_WHITE);
        gui.drawText(new Position(7, 1), "15:45:21", FALLBOUND_WHITE); // placeholder
        gui.drawText(new Position(2, 2), "HIGHSCORE", FALLBOUND_LIGHT_GRAY);
        gui.drawText(new Position(12, 2), "17:21:11", FALLBOUND_LIGHT_GRAY); // placeholder
        gui.drawText(new Position(83, 1), "COINS", FALLBOUND_WHITE);
        gui.drawText(new Position(81, 1), String.valueOf(getModel().getPlayer().getCollectedCoins()), FALLBOUND_GOLD);
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        getModel().getWalls().forEach(wall -> wallViewer.draw(gui, (Wall) wall));
        playerViewer.draw(gui, getModel().getPlayer());
        getModel().getCoins().forEach(coin -> coinViewer.draw(gui, coin));
    }
}
