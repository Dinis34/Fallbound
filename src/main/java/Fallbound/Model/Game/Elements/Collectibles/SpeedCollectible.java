package Fallbound.Model.Game.Elements.Collectibles;

import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class SpeedCollectible extends Collectible {
    public SpeedCollectible(Vector position, Scene scene) {
        super(position, scene);
    }

    @Override
    public int getCost() {
        return 20;
    }

    @Override
    public String getIcon() {
        return "⚡";
    }

    @Override
    public String getDescription() {
        return "Increases move speed";
    }

    @Override
    public void onCollect(Player player) {
        player.setMoveSpeed(player.getMoveSpeed() + 0.3);
    }
}
