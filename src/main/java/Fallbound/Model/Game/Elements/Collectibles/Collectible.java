package Fallbound.Model.Game.Elements.Collectibles;

import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public abstract class Collectible extends Element {
    private final Scene scene;

    public Collectible(Vector position, Scene scene) {
        super(position);
        this.scene = scene;
    }

    public abstract int getCost();

    public abstract String getIcon();

    public abstract String getDescription();

    public boolean shouldShowDescription() {
        return scene.isColliding(scene.getPlayer().getPosition(), this.getPosition());
    }

    public abstract void onCollect(Player player);
}