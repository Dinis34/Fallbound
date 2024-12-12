package Fallbound.Model.Game.Elements.Enemies;

import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class FloatingEnemy extends Enemy implements Shootable, Stompable {
    private final Scene scene;
    private final long moveCooldown = 200;
    private long lastMoveTime = 0;

    public FloatingEnemy(Vector position, Scene scene) {
        super(position);
        this.scene = scene;
    }

    @Override
    public void followPlayer() {
        Vector nextPosition = null;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime < moveCooldown) return;
        lastMoveTime = currentTime;

        Vector playerPos = scene.getPlayer().getPosition();
        Vector currentPos = this.getPosition();

        double x_distance = playerPos.getX() - currentPos.getX();
        double y_distance = playerPos.getY() - currentPos.getY();

        double x_unity = x_distance == 0 ? 0 : x_distance / Math.abs(x_distance);
        double y_unity = y_distance == 0 ? 0 : y_distance / Math.abs(y_distance);

        if (Math.abs(x_distance) >= Math.abs(y_distance)) {
            nextPosition = new Vector(currentPos.getX() + x_unity, currentPos.getY());
        } else if (Math.abs(y_distance) > 0) {
            nextPosition = new Vector(currentPos.getX(), currentPos.getY() + y_unity);
        }

        boolean canMove = true;
        for (Element wall : scene.getWalls()) {
            if (scene.isColliding(nextPosition, wall.getPosition())) {
                canMove = false;
                break;
            }
        }

        if (scene.isColliding(nextPosition, scene.getPlayer().getPosition())) {
            // TODO damage player
            canMove = false;
        }

        if (canMove) {
            this.setPosition(nextPosition);
        }
    }

}
