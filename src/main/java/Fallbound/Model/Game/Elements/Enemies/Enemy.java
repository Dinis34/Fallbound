package Fallbound.Model.Game.Elements.Enemies;

import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Vector;

public abstract class Enemy extends Element {
    public Enemy(Vector position) {
        super(position);
    }

    public abstract void move();
}
