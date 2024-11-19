package Fallbound.Model.Game.Elements;

import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class Player extends Element {
    private final double GRAVITY = 0.02;
    private final double JUMP_FORCE = -0.4;
    private final double MAX_FALL_SPEED = 0.4;
    private final double MOVE_SPEED = 0.5;

    private final Vector velocity;
    private final Scene scene;
    private boolean onGround = false;
    private int collectedCoins = 0;

    public Player(Vector position, Scene scene) {
        super(position);
        this.scene = scene;
        this.velocity = new Vector(0, 0);
    }

    public int getCollectedCoins() {
        return collectedCoins;
    }

    public Boolean isOnGround() {
        return onGround;
    }

    public void gravity() {
        if (!onGround) {
            velocity.setY(velocity.getY() + GRAVITY);
            velocity.setY(Math.min(velocity.getY(), MAX_FALL_SPEED));
        }
    }

    public void moveLeft() {
        velocity.setX(-MOVE_SPEED);
    }

    public void moveRight() {
        velocity.setX(MOVE_SPEED);
    }

    public void stop() {
        velocity.setX(0);
    }

    public void jump() {
        velocity.setY(JUMP_FORCE);
        this.onGround = false;
    }

    public void move() {
        Vector nextPosition = getPosition().add(velocity);
        boolean canMove = true;
        for (Element wall : scene.getWalls()) {
            if (scene.isColliding(nextPosition, wall.getPosition())) {
                canMove = false;
            }
        }
        if (canMove) {
            setPosition(nextPosition);
        } else {
            velocity.setX(0);
            velocity.setY(0);
            gravity();
            nextPosition = getPosition().add(velocity);
            setPosition(nextPosition);
        }
    }

    public void update() {
        gravity();
        move();
        handleCollisions();
    }

    public boolean checkBottomCollision() {
        boolean isColliding = false;
        for (Element element : scene.getWalls()) {
            if (scene.isColliding(getPosition(), element.getPosition().add(new Vector(0, -1)))) {
                isColliding = true;
                velocity.setY(0);
            }
        }
        return isColliding;
    }

    public void checkCoinCollision() {
        for (Coin coin : scene.getCoins()) {
            if (scene.isColliding(coin.getPosition(), getPosition())) {
                scene.removeCoin(coin);
                collectedCoins++;
                break;
            }
        }
    }

    public void handleCollisions() {
        onGround = checkBottomCollision();
        checkCoinCollision();
    }
}
