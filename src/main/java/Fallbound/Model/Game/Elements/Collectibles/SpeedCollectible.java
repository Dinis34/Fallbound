package Fallbound.Model.Game.Elements.Collectibles;

import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Vector;

public class SpeedCollectible extends Collectible {
    private final int cost = 20;
    private final String icon = "⚡";
    private final String description = "Increases move speed";

    public SpeedCollectible(Vector position) {
        super(position);
    }

    @Override
    public void onCollect(Player player) {
        player.setMoveSpeed(player.getMoveSpeed() + 0.3);
    }
}
