package Fallbound.Controller.Game.Elements;

import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Elements.Enemies.Enemy;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class GroundedEnemyController extends EnemyController {
    private long lastMoveTime = 0;
    private int direction = 1;

    public GroundedEnemyController(Enemy enemy, Scene scene) {
        super(enemy, scene);
    }

    @Override
    public void move() {
        long currentTime = System.currentTimeMillis();
        long moveCooldown = 500;
        if (currentTime - lastMoveTime < moveCooldown) return;
        lastMoveTime = currentTime;

        Vector currentPos = enemy.getPosition();
        Vector nextPosition = new Vector(currentPos.getX() + direction, currentPos.getY());

        boolean canMove = false;
        Vector belowNextPosition = new Vector(nextPosition.getX(), nextPosition.getY() + 1);
        for (Element wall : scene.getWalls()) {
            if (scene.isColliding(belowNextPosition, wall.getPosition())) {
                canMove = true;
                break;
            }
        }

        if (scene.isColliding(nextPosition, scene.getPlayer().getPosition())) {
            scene.getPlayer().takeDamage();
            canMove = false;
        }

        if (!canMove) {
            direction = -direction;
        } else {
            enemy.setPosition(nextPosition);
        }
    }
}
