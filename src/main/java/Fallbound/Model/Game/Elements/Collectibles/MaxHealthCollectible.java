package Fallbound.Model.Game.Elements.Collectibles;

import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Vector;

public class MaxHealthCollectible extends Collectible {
    private final int cost = 15;
    private final String icon = "♡";
    private final String description = "Increases max health";

    public MaxHealthCollectible(Vector position) {
        super(position);
    }

    @Override
    public void onCollect(Player player) {
        player.setMaxHealth(player.getMaxHealth() + 1);
    }
}
