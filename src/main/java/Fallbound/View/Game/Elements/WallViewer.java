package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Wall;
import Fallbound.View.Theme;

public class WallViewer implements ElementViewer<Wall> {

    @Override
    public void draw(GUI gui, Wall element, int offset) {
        char coverWallChar = '▓';
        String coverWallColor = Theme.FALLBOUND_WHITE;
        gui.drawText(element.getPosition().toPosition().applyOffset(offset), String.valueOf(coverWallChar), coverWallColor);
    }
}
