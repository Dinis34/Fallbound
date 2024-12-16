package Fallbound.Model.Game.Elements.Enemies;

import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class ShellEnemy extends Enemy implements Stompable {
    private final Scene scene;
    private final long moveCooldown;
    private int direction = 1;
    private long lastMoveTime = 0;

    public ShellEnemy(Vector position, Scene scene) {
        super(position);
        this.scene = scene;
        moveCooldown = 500;
    }

    @Override
    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime < moveCooldown) return;
        lastMoveTime = currentTime;

        Vector currentPos = this.getPosition();
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
            this.setPosition(nextPosition);
        }
    }
}