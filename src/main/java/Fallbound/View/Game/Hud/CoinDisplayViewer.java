package Fallbound.View.Game.Hud;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Hud.CoinDisplay;

import static Fallbound.View.Theme.FALLBOUND_GOLD;

public class CoinDisplayViewer implements HudViewer<CoinDisplay> {
    @Override
    public void draw(GUI gui, CoinDisplay display) {
        gui.drawText(display.getPosition(), "coins: " + display.getCoincount(), FALLBOUND_GOLD);
    }
}
