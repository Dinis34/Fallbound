package Fallbound.View.Game.Hud;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Hud.Time;

import static Fallbound.View.Theme.FALLBOUND_WHITE;


public class TimeViewer implements HudViewer<Time>{
    @Override
    public void draw(GUI gui, Time time) {
        gui.drawText(time.getPosition(), "time: " + time.getTime(), FALLBOUND_WHITE);
    }
}
