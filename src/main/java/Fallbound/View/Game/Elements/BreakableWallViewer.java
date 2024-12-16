package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.BreakableWall;
import Fallbound.View.Theme;

public class BreakableWallViewer implements ElementViewer<BreakableWall> {

    @Override
    public void draw(GUI gui, BreakableWall element, int offset) {
        char coverWallChar = '░';
        String coverWallColor = Theme.FALLBOUND_WHITE;
        gui.drawText(element.getPosition().toPosition().applyOffset(offset), String.valueOf(coverWallChar), coverWallColor);
    }
}
