package Fallbound.Model.Game.Elements;

import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class Player extends Element {
    private final double GRAVITY = 0.02;
    private final double JUMP_FORCE = -0.4;
    private final double MAX_FALL_SPEED = 0.5;
    private final double MOVE_SPEED = 0.8;

    private final Vector velocity;
    private boolean onGround = false;

    private final Scene scene;

    public Player(Vector position, Scene scene) {
        super(position);
        this.scene = scene;
        this.velocity = new Vector(0, 0);
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
        setPosition(getPosition().add(velocity));
    }

    public void update() {
        gravity();
        move();
        checkCollision();
    }

    public void checkCollision() {
        boolean isColliding = false;
        for (Element element : scene.getWalls()) {
            if (scene.isColliding(getPosition(), element.getPosition().add(new Vector(0, 1)))) {
                isColliding = true;
                velocity.setY(0);
            }
        }
        this.onGround = isColliding;
    }

}
