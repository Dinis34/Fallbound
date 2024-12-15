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
        return 15;
    }

    @Override
    public String getIcon() {
        return "♡";
    }

    @Override
    public String getDescription() {
        return "Increases max health";
    }

    @Override
    public void onCollect(Player player) {
        player.setMaxHealth(player.getMaxHealth() + 1);
    }
}
