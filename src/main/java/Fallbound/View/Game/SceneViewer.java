package Fallbound.View.Game;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Tiles.Wall;
import Fallbound.Model.Game.Scene;
import Fallbound.View.Game.Elements.CoinViewer;
import Fallbound.View.Game.Elements.PlayerViewer;
import Fallbound.View.Game.Elements.WallViewer;
import Fallbound.View.Viewer;

public class SceneViewer extends Viewer<Scene> {

    private final WallViewer wallViewer = new WallViewer();
    private final PlayerViewer playerViewer = new PlayerViewer();
    private final CoinViewer coinViewer = new CoinViewer();


    public SceneViewer(Scene model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        getModel().getWalls().forEach(wall -> wallViewer.draw(gui, (Wall) wall));
        playerViewer.draw(gui, getModel().getPlayer());
        getModel().getCoins().forEach(coin -> coinViewer.draw(gui, coin));
    }
}
