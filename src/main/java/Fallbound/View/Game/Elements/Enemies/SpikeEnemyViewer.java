package Fallbound.View.Game.Elements.Enemies;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Enemies.Enemy;
import Fallbound.View.Theme;

public class SpikeEnemyViewer extends EnemyViewer {
    @Override
    public void draw(GUI gui, Enemy enemy, int offset) {
        char coverEnemyChar = '\u0394';
        String coverEnemyColor = Theme.FALLBOUND_RED;
        gui.drawText(enemy.getPosition().toPosition().applyOffset(offset), String.valueOf(coverEnemyChar), coverEnemyColor);
    }
}
