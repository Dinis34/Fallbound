package Fallbound.Model.Game.Elements.Enemies;

import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class FloatingEnemy extends Enemy {
    private Scene scene;

    public FloatingEnemy(Vector position, Scene scene) {
        super(position);
        this.scene = scene;
    }

    public void followPlayer() {
        double x_distance = this.getPosition().getX() - scene.getPlayer().getPosition().getX();
        double y_distance = this.getPosition().getY() - scene.getPlayer().getPosition().getY();
        double x_unity = x_distance / Math.abs(x_distance);
        double y_unity = y_distance / Math.abs(y_distance);
        if (Math.abs(x_distance) >= Math.abs(y_distance)) {
            this.setPosition(new Vector(this.getPosition().getX() , this.getPosition().getY() + y_unity));
        } else {
            this.setPosition(new Vector(this.getPosition().getX() + x_unity, this.getPosition().getY()));
        }
    }

}
