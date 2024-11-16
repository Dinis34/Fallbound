package Fallbound.View.Game;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Scene;
import Fallbound.View.Game.Elements.WallViewer;
import Fallbound.View.Viewer;

public class SceneViewer extends Viewer<Scene> {

    private WallViewer wallViewer = new WallViewer();

    public SceneViewer(Scene model) {
        super(model);
    }

    @Override
    protected void drawElements(GUI gui, long time) {
        getModel().getWalls().forEach(wall -> wallViewer.draw(gui, wall));
    }
}
