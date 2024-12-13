package Fallbound.Model.Game.Elements;

import Fallbound.Model.Game.Elements.Enemies.Enemy;
import Fallbound.Model.Game.Elements.Enemies.Stompable;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class Player extends Element {
    private final double GRAVITY;
    private final double JUMP_FORCE = -0.4;
    private final double MAX_FALL_SPEED;
    private final double MOVE_SPEED = 0.5;

    private final long SHOOT_COOLDOWN;
    private final Vector velocity;
    private final Scene scene;
    private long lastShotTime = 0;
    private boolean onGround = false;
    private int collectedCoins = 0;
    private Vector lastPosition;

    public Player(Vector position, Scene scene) {
        super(position);
        this.scene = scene;
        this.velocity = new Vector(0, 0);
        GRAVITY = 0.02;
        MAX_FALL_SPEED = 0.4;
        SHOOT_COOLDOWN = 350;
    }

    public Boolean getOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public int getCollectedCoins() {
        return collectedCoins;
    }

    public void gravity() {
        if (!onGround) {
            velocity.setY(Math.min(velocity.getY() + GRAVITY, MAX_FALL_SPEED));
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
        lastShotTime = System.currentTimeMillis();
        velocity.setY(JUMP_FORCE);
        this.onGround = false;
    }

    public void move() {
        Vector nextPosition = getPosition().add(velocity);

        Vector horizontalPosition = new Vector(nextPosition.getX(), getPosition().getY());
        if (cantMoveTo(horizontalPosition)) {
            velocity.setX(0);
        } else {
            this.lastPosition = getPosition();
            setPosition(new Vector(horizontalPosition.getX(), getPosition().getY()));
        }

        Vector verticalPosition = new Vector(getPosition().getX(), nextPosition.getY());
        if (cantMoveTo(verticalPosition)) {
            velocity.setY(0);
        } else {
            this.lastPosition = getPosition();
            setPosition(new Vector(getPosition().getX(), verticalPosition.getY()));
        }
    }

    private boolean cantMoveTo(Vector position) {
        if (position.getX() < 0 || position.getX() > scene.getWidth() - 1) {
            return true;
        }
        for (Element wall : scene.getWalls()) {
            if (scene.isColliding(position, wall.getPosition())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkBottomCollision() {
        for (Element element : scene.getWalls()) {
            if (scene.isColliding(getPosition(), element.getPosition().add(new Vector(0, -1)))) {
                velocity.setY(0);
                return true;
            }
        }
        return false;
    }

    private void checkCoinCollision() {
        for (Element coin : scene.getCoins()) {
            if (scene.isColliding(coin.getPosition(), getPosition())) {
                scene.removeCoin((Coin) coin);
                collectedCoins++;
                break;
            }
        }
    }

    public void checkEnemyCollision() {
        for (Element enemy : scene.getEnemies()) {
            if (scene.isColliding(getPosition(), enemy.getPosition())) {
                if (scene.isColliding(lastPosition, enemy.getPosition().subtract(new Vector(0, 1))) ||
                        scene.isColliding(lastPosition, enemy.getPosition().subtract(new Vector(1, 1))) ||
                        scene.isColliding(lastPosition, enemy.getPosition().subtract(new Vector(-1, 1)))) {
                    if (enemy instanceof Stompable) {
                        scene.removeEnemy((Enemy) enemy);
                        velocity.setY(JUMP_FORCE / 1.5);
                    } else {
                        // TODO damage player
                        System.out.println("player: auch!");
                        velocity.setY(JUMP_FORCE / 1.5);
                    }
                } else {
                    // TODO damage player
                    System.out.println("player: auch!");
                    velocity.setY(JUMP_FORCE / 1.5);
                    velocity.setX(0);
                }
                break;
            }
        }
    }

    private void handleCollisions() {
        onGround = checkBottomCollision();
        checkCoinCollision();
        checkEnemyCollision();
    }

    public Scene getScene() {
        return scene;
    }

    public void update() {
        gravity();
        move();
        handleCollisions();
    }

    public void shoot() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime >= SHOOT_COOLDOWN) {
            scene.addBullet(new Bullet(getPosition().add(new Vector(0, -1 - scene.getCameraOffset()))));
            lastShotTime = currentTime;
            velocity.setY(-0.175); // recoil
        }
    }
}
