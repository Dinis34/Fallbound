package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Wall;
import Fallbound.View.Theme;

public class WallViewer implements ElementViewer<Wall> {


    @Override
    public void draw(GUI gui, Wall element) {
        char coverWallChar = '\u2593';
        String coverWallColor = Theme.FALLBOUND_WHITE;
        gui.drawElement(element.getPosition(), coverWallChar, coverWallColor);
    }
}
