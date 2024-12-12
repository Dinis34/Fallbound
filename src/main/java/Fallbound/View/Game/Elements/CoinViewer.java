package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Coin;
import Fallbound.View.Theme;

public class CoinViewer implements ElementViewer<Coin> {
    @Override
    public void draw(GUI gui, Coin element, int offset) {
        gui.drawText(element.getPosition().toPosition().applyOffset(offset), "O", Theme.FALLBOUND_GOLD);
    }
}