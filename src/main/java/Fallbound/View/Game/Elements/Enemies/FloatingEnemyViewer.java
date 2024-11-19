package Fallbound.View.Game.Elements.Enemies;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Enemies.FloatingEnemy;
import Fallbound.View.Game.Elements.ElementViewer;
import Fallbound.View.Theme;

public class FloatingEnemyViewer implements ElementViewer<FloatingEnemy> {
    @Override
    public void draw(GUI gui, FloatingEnemy element) {
        char coverEnemyChar = '\u8461';
        String coverEnemyColor = Theme.FALLBOUND_RED;
        gui.drawText(element.getPosition().toPosition(), String.valueOf(coverEnemyChar), coverEnemyColor);
    }
}
