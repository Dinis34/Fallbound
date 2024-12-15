package Fallbound.Model.Game.Elements.Collectibles;

import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class JumpCollectible extends Collectible{
    public JumpCollectible(Vector position, Scene scene) {
        super(position, scene);
    }

    @Override
    public int getCost() {
        return 15;
    }

    @Override
    public String getIcon() {
        return "⬆";
    }

    @Override
    public String getDescription() {
        return "increases jump height";
    }

    @Override
    public void onCollect(Player player) {
        player.setJumpForce(player.getJumpForce() - 0.1);
    }
}
