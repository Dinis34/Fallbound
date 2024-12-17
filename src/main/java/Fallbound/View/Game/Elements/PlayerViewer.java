package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Vector;
import Fallbound.View.Theme;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public void draw(GUI gui, Player element, int offset) {
        String[] trailColors = {"#001519", "#001f3f", "#00275d", "#003082", "#0039a3", "#0040BF"};
        int i = 0;
        for (Vector position : element.getLastPositions()) {
            gui.drawText(position.toPosition().applyOffset(offset), "█", trailColors[i]);
            i++;
        }
        long currentTime = System.currentTimeMillis();
        boolean isInvincible = (currentTime - element.getLastDamageTime()) < element.getDamageCooldown();
        String color = isInvincible ? Theme.FALLBOUND_LIGHT_GRAY : Theme.FALLBOUND_BLUE;
        gui.drawText(element.getPosition().toPosition().applyOffset(offset), "█", color);
    }
}