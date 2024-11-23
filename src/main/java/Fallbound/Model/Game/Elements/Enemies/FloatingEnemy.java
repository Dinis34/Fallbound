package Fallbound.Model.Game.Elements.Enemies;

import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class FloatingEnemy extends Enemy {
    private final Scene scene;

    public FloatingEnemy(Vector position, Scene scene) {
        super(position);
        this.scene = scene;
    }

    public void followPlayer() {
        Vector playerPos = scene.getPlayer().getPosition();
        Vector currentPos = this.getPosition();

        double x_distance = currentPos.getX() - playerPos.getX();
        double y_distance = currentPos.getY() - playerPos.getY();

        double x_unity = x_distance / Math.abs(x_distance);
        double y_unity = y_distance/ Math.abs(y_distance);

        if (Math.abs(x_distance) >= Math.abs(y_distance) || x_distance == 0) {
            this.setPosition(new Vector(currentPos.getX() , currentPos.getY() + y_unity));
        } else {
            this.setPosition(new Vector(currentPos.getX() + x_unity, currentPos.getY()));
        }
        double newX = Math.max(0, Math.min(scene.getWidth() - 1, getPosition().getX()));
        double newY = Math.max(0, Math.min(scene.getHeight() - 1, getPosition().getY()));
        this.setPosition(new Vector(newX, newY));
    }

}
