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
        if (scene == null) {
            throw new IllegalArgumentException("Scene cannot be null");
        }
        this.scene = scene;
    }

    @Override
    public void followPlayer() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime < moveCooldown) {
            return;
        }
        lastMoveTime = currentTime;

        Vector playerPos = scene.getPlayer().getPosition();
        Vector currentPos = this.getPosition();

        Vector direction = calculateDirection(playerPos, currentPos);
        Vector nextPosition = determineNextPosition(currentPos, direction);

        if (nextPosition != null && canMoveTo(nextPosition)) {
            this.setPosition(nextPosition);
        }
    }

    private Vector calculateDirection(Vector playerPos, Vector currentPos) {
        double xDistance = playerPos.getX() - currentPos.getX();
        double yDistance = playerPos.getY() - currentPos.getY();

        double xUnity = xDistance == 0 ? 0 : xDistance / Math.abs(xDistance);
        double yUnity = yDistance == 0 ? 0 : yDistance / Math.abs(yDistance);

        if (Math.abs(xDistance) >= Math.abs(yDistance)) {
            return new Vector(xUnity, 0);
        } else {
            return new Vector(0, yUnity);
        }
    }

    private Vector determineNextPosition(Vector currentPos, Vector direction) {
        if (direction == null) {
            return null;
        }
        return new Vector(currentPos.getX() + direction.getX(), currentPos.getY() + direction.getY());
    }

    private boolean canMoveTo(Vector nextPosition) {
        for (Element wall : scene.getWalls()) {
            if (scene.isColliding(nextPosition, wall.getPosition())) {
                return false;
            }
        }

        // TODO damage player
        return !scene.isColliding(nextPosition, scene.getPlayer().getPosition());
    }
}
