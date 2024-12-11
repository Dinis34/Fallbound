package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Bullet;
import Fallbound.View.Theme;

public class BulletViewer implements ElementViewer<Bullet> {
    @Override
    public void draw(GUI gui, Bullet element, int offset) {
        gui.drawElement(element.getPosition().toPosition(), '.', Theme.FALLBOUND_RED);
    }
}
