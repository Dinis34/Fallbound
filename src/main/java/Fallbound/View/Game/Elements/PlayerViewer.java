package Fallbound.View.Game.Elements;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.View.Theme;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public void draw(GUI gui, Player element, int offset) {
        long currentTime = System.currentTimeMillis();
        boolean isInvincible = (currentTime - element.getLastDamageTime()) < element.getDamageCooldown();
        String color = isInvincible ? Theme.FALLBOUND_LIGHT_GRAY : Theme.FALLBOUND_BLUE;
        gui.drawText(element.getPosition().toPosition().applyOffset(offset), "\u2588", color);
    }
}