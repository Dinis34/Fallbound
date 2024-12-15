package Fallbound.Model.Game.Elements.Collectibles;

import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class BulletCountCollectible extends Collectible {
    public BulletCountCollectible(Vector position, Scene scene) {
        super(position, scene);
    }

    @Override
    public int getCost() {
        return 10;
    }

    @Override
    public String getIcon() {
        return "|";
    }

    @Override
    public String getDescription() {
        return "increases bullet count";
    }

    @Override
    public void onCollect(Player player) {
        player.setMaxNumBullets(player.getMaxNumBullets() + 1);
    }
}
