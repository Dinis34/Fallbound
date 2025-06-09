package Fallbound.Model.Game.Elements.Collectibles;

import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class MaxHealthCollectible extends Collectible {
    public MaxHealthCollectible(Vector position, Scene scene) {
        super(position, scene);
    }

    @Override
    public int getCost() {
        return 7;
    }

    @Override
    public String getIcon() {
        return "♡";
    }

    @Override
    public String getDescription() {
        return "increases max health";
    }

    @Override
    public void onCollect(Player player) {
        player.setMaxHealth(player.getMaxHealth() + 1);
        if (player.getHealth() < player.getMaxHealth()) {
            player.setHealth(player.getHealth() + 1);
        }
    }
}
