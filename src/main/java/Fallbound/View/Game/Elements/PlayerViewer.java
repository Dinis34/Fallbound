package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.View.Theme;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public void draw(GUI gui, Player element, int offset) {
        gui.drawText(element.getPosition().toPosition().applyOffset(offset), "\u2588", Theme.FALLBOUND_BLUE);
    }
}
