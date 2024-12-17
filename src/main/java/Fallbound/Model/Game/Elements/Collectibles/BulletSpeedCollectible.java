package Fallbound.Model.Game.Elements.Collectibles;

import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class BulletSpeedCollectible extends Collectible {
    public BulletSpeedCollectible(Vector position, Scene scene) {
        super(position, scene);
    }

    @Override
    public int getCost() {
        return 8;
    }

    @Override
    public String getIcon() {
        return "⇢";
    }

    @Override
    public String getDescription() {
        return "increases bullet speed";
    }

    @Override
    public void onCollect(Player player) {
        if (player.getShootCooldown() > 71) {
            player.setShootCooldown(player.getShootCooldown() - 70);
        }
    }
}
