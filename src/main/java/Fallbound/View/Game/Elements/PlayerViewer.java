package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Position;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public void draw(GUI gui, Player element) {
        gui.drawText(new Position(element.getPosition().getX(), element.getPosition().getY()), "P", "#FFFFFF");
    }
}
