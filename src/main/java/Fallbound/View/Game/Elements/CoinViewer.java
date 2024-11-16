package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Coin;
import Fallbound.View.Theme;

public class CoinViewer implements ElementViewer<Coin>{
    @Override
    public void draw(GUI gui, Coin element){
        char coverCoinchar = '\u0169';
        String coverCoinColor = Theme.FALLBOUND_GOLD;
        gui.drawElement(element.getPosition(), coverCoinchar, coverCoinColor);
    }

}