package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.View.Theme;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public void draw(GUI gui, Player element) {
        gui.drawText(element.getPosition().toPosition(), "⬛", Theme.FALLBOUND_PURPLE);
    }
}
