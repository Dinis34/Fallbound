package Fallbound.Model.Game.Elements.Collectibles;

import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class HealthCollectible extends Collectible {
    public HealthCollectible(Vector position, Scene scene) {
        super(position, scene);
    }

    @Override
    public int getCost() {
        return 10;
    }

    @Override
    public String getIcon() {
        return "♥";
    }

    @Override
    public String getDescription() {
        return "increases health";
    }

    @Override
    public void onCollect(Player player) {
        if (player.getHealth() < player.getMaxHealth()) {
            player.setHealth(player.getHealth() + 1);
        }
    }
}
