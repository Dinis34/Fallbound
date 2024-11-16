package Fallbound.View.Game.Hud;
import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Hud.Hud;


public interface HudViewer<T extends Hud>{
    void draw(GUI gui, T element);
}
