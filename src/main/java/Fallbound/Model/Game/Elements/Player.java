package Fallbound.Model.Game.Elements;

import Fallbound.Controller.Sound.SoundController;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Sound.SoundOption;
import Fallbound.Model.Vector;

public class Player extends Element {
    private static final double GRAVITY = 0.02;
    private static final double MAX_FALL_SPEED = 0.4;

    private static final long DAMAGE_COOLDOWN = 3000;
    private static final int DEFAULT_MAX_HEALTH = 5;
    private static final int DEFAULT_MAX_BULLETS = 5;
    private static final long DEFAULT_SHOOT_COOLDOWN = 350;
    private static final double DEFAULT_MOVE_SPEED = 0.45;
    private static final double DEFAULT_JUMP_FORCE = -0.4;
    private static final double SHOOTING_RECOIL = -0.175;

    private Scene scene;
    private Vector velocity;
    private Vector lastPosition;

    private int health;
    private int maxHealth;
    private double moveSpeed;
    private double jumpForce;

    private int numBullets;
    private int maxNumBullets;
    private long shootCooldown;
    private long lastShotTime;

    private boolean onGround;
    private long lastDamageTime;

    private int collectedCoins;

    public Player(Vector position, Scene scene) {
        super(position);
        initializePlayerState(scene);
    }

    @Override
    public void setPosition(Vector position) {
        this.lastPosition = this.getPosition();
        super.setPosition(position);
    }

    public static long getDamageCooldown() {
        return DAMAGE_COOLDOWN;
    }

    private void initializePlayerState(Scene scene) {
        this.scene = scene;
        this.velocity = new Vector(0, 0);

        this.maxHealth = DEFAULT_MAX_HEALTH;
        this.health = maxHealth;
        this.maxNumBullets = DEFAULT_MAX_BULLETS;
        this.numBullets = maxNumBullets;
        this.shootCooldown = DEFAULT_SHOOT_COOLDOWN;
        this.moveSpeed = DEFAULT_MOVE_SPEED;
        this.jumpForce = DEFAULT_JUMP_FORCE;

        this.lastShotTime = 0;
        this.lastDamageTime = 0;
        this.onGround = false;
        this.collectedCoins = 0;
    }

    public void moveLeft() {
        velocity.setX(-moveSpeed);
    }

    public void moveRight() {
        velocity.setX(moveSpeed);
    }

    public void stop() {
        velocity.setX(0);
    }

    public void jump() {
        lastShotTime = System.currentTimeMillis();
        velocity.setY(jumpForce);
        this.onGround = false;
        SoundController.getInstance().playSound(SoundOption.JUMP);
    }

    public void gravity() {
        if (!onGround) {
            velocity.setY(Math.min(velocity.getY() + GRAVITY, MAX_FALL_SPEED));
        }
    }

    public void move() {
        Vector nextPosition = getPosition().add(velocity);

        Vector horizontalPosition = new Vector(nextPosition.getX(), getPosition().getY());
        if (cantMoveTo(horizontalPosition)) {
            velocity.setX(0);
        } else {
            updatePosition(horizontalPosition);
        }

        Vector verticalPosition = new Vector(getPosition().getX(), nextPosition.getY());
        if (cantMoveTo(verticalPosition)) {
            velocity.setY(0);
        } else {
            updatePosition(verticalPosition);
        }
    }

    private void updatePosition(Vector newPosition) {
        this.lastPosition = getPosition();
        setPosition(newPosition);
    }

    private boolean cantMoveTo(Vector position) {
        if (position.getX() < 0 || position.getX() > scene.getWidth() - 1) {
            return true;
        }

        return scene.getWalls().stream()
                .anyMatch(wall -> scene.isColliding(position, wall.getPosition()));
    }

    public void shoot() {
        long currentTime = System.currentTimeMillis();
        if (canShoot(currentTime)) {
            performShot(currentTime);
        }
    }

    private boolean canShoot(long currentTime) {
        return numBullets > 0 &&
                currentTime - lastShotTime >= shootCooldown;
    }

    private void performShot(long currentTime) {
        SoundController.getInstance().playSound(SoundOption.BULLET);
        scene.addBullet(new Bullet(getPosition().add(new Vector(0, -1 - scene.getCameraOffset()))));
        lastShotTime = currentTime;
        numBullets--;
        velocity.setY(SHOOTING_RECOIL);
    }

    public void takeDamage() {
        long currentTime = System.currentTimeMillis();
        if (canTakeDamage(currentTime)) {
            SoundController.getInstance().playSound(SoundOption.PLAYER_DAMAGE);
            health--;
            lastDamageTime = currentTime;
        }
    }

    private boolean canTakeDamage(long currentTime) {
        return currentTime - lastDamageTime >= DAMAGE_COOLDOWN && health > 0;
    }

    public void update() {
        gravity();
        move();
    }

    public Scene getScene() {
        return scene;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public Vector getLastPosition() {
        return lastPosition;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public double getJumpForce() {
        return jumpForce;
    }

    public void setJumpForce(double jumpForce) {
        this.jumpForce = jumpForce;
    }

    public int getNumBullets() {
        return numBullets;
    }

    public void setNumBullets(int numBullets) {
        this.numBullets = numBullets;
    }

    public int getMaxNumBullets() {
        return maxNumBullets;
    }

    public void setMaxNumBullets(int maxNumBullets) {
        this.maxNumBullets = maxNumBullets;
    }

    public long getShootCooldown() {
        return shootCooldown;
    }

    public void setShootCooldown(long shootCooldown) {
        this.shootCooldown = shootCooldown;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public long getLastDamageTime() {
        return lastDamageTime;
    }

    public int getCollectedCoins() {
        return collectedCoins;
    }

    public void setCollectedCoins(int collectedCoins) {
        this.collectedCoins = collectedCoins;
    }
}