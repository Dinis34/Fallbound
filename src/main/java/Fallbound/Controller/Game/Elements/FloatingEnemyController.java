package Fallbound.Controller.Game.Elements;

import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Elements.Enemies.Enemy;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class FloatingEnemyController extends EnemyController {
    private long lastMoveTime = 0;
    private final long moveCooldown;

    public FloatingEnemyController(Enemy enemy, Scene scene, long moveCooldown) {
        super(enemy, scene);
        this.moveCooldown = moveCooldown;
    }

    @Override
    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime < moveCooldown) {
            return;
        }
        lastMoveTime = currentTime;

        Vector playerPos = scene.getPlayer().getPosition();
        Vector currentPos = enemy.getPosition();

        Vector direction = calculateDirection(playerPos, currentPos);
        Vector nextPosition = determineNextPosition(currentPos, direction);

        if (canMoveTo(nextPosition)) {
            enemy.setPosition(nextPosition);
        }
    }

    private Vector calculateDirection(Vector playerPos, Vector currentPos) {
        double xDistance = playerPos.getX() - currentPos.getX();
        double yDistance = playerPos.getY() - currentPos.getY();

        double xUnity = xDistance == 0 ? 0 : xDistance / Math.abs(xDistance);
        double yUnity = yDistance == 0 ? 0 : yDistance / Math.abs(yDistance);

        return Math.abs(xDistance) >= Math.abs(yDistance) ? new Vector(xUnity, 0) : new Vector(0, yUnity);
    }

    private Vector determineNextPosition(Vector currentPos, Vector direction) {
        return new Vector(currentPos.getX() + direction.getX(), currentPos.getY() + direction.getY());
    }

    private boolean canMoveTo(Vector nextPosition) {
        for (Element wall : scene.getWalls()) {
            if (scene.isColliding(nextPosition, wall.getPosition())) {
                return false;
            }
        }

        if (scene.isColliding(nextPosition, scene.getPlayer().getPosition())) {
            scene.getPlayer().takeDamage();
            return false;
        }
        return true;
    }
}
