package Fallbound.Model.Game.Elements.Collectibles;

import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Vector;

public abstract class Collectible extends Element {
    int cost;
    private String icon;
    private String description;

    public Collectible(Vector position) {
        super(position);
    }

    int getCost() {
        return this.cost;
    }

    String getIcon() {
        return this.icon;
    }

    String getDescription() {
        return this.description;
    }

    abstract void onCollect(Player player);
}