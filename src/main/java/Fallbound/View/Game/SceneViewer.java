package Fallbound.View.Game;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Position;
import Fallbound.View.Game.Elements.PlayerViewer;
import Fallbound.View.Game.Elements.WallViewer;
import Fallbound.View.Viewer;

import static Fallbound.View.Theme.*;

public class SceneViewer extends Viewer<Scene> {

    private WallViewer wallViewer = new WallViewer();
    private PlayerViewer playerViewer = new PlayerViewer();

    public SceneViewer(Scene model) {
        super(model);
    }
    @Override
    protected void drawHud(GUI gui){
        gui.drawText(new Position(0, 0), "TIME: ", FALLBOUND_WHITE);
        gui.drawText(new Position(6, 0), String.valueOf(0), FALLBOUND_WHITE); // 0 porque ainda n tem timer
        gui.drawText(new Position(0, 1), "HIGHSCORE: ", FALLBOUND_LIGHT_GRAY);
        gui.drawText(new Position(12, 1), String.valueOf(getModel().getScore()), FALLBOUND_LIGHT_GRAY);
        gui.drawText(new Position(83, 0), String.valueOf(getModel().getCoincount()), FALLBOUND_GOLD);
        gui.drawText(new Position(85, 0), "COINS" ,FALLBOUND_WHITE);
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        getModel().getWalls().forEach(wall -> wallViewer.draw(gui, wall));
        playerViewer.draw(gui, getModel().getPlayer());
    }
}
