package Fallbound.View.Game;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Scene;
import Fallbound.View.Game.Elements.PlayerViewer;
import Fallbound.View.Game.Elements.WallViewer;
import Fallbound.View.Game.Hud.HighScoreViewer;
import Fallbound.View.Game.Hud.TimeViewer;
import Fallbound.View.Viewer;

public class SceneViewer extends Viewer<Scene> {

    private WallViewer wallViewer = new WallViewer();
    private PlayerViewer playerViewer = new PlayerViewer();
    private HighScoreViewer highScoreViewer = new HighScoreViewer();
    private TimeViewer timeViewer = new TimeViewer();

    public SceneViewer(Scene model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        getModel().getWalls().forEach(wall -> wallViewer.draw(gui, wall));
        playerViewer.draw(gui, getModel().getPlayer());
        highScoreViewer.draw(gui, getModel().getHighScore());
        timeViewer.draw(gui, getModel().getTime());
    }
}
