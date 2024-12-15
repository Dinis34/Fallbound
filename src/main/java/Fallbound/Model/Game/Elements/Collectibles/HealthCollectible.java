package Fallbound.Model.Game.Elements.Collectibles;

import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Vector;

public class HealthCollectible extends Collectible {
    private final int cost = 10;
    private final String icon = "♥";
    private final String description = "Increases health";

    public HealthCollectible(Vector position) {
        super(position);
    }

    @Override
    public void onCollect(Player player) {
        if (player.getHealth() < player.getMaxHealth()) {
            player.setHealth(player.getHealth() + 1);
        }
    }
}
