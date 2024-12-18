package Fallbound.Model.Game;

import Fallbound.Controller.Sound.SoundController;
import Fallbound.Model.Game.Elements.*;
import Fallbound.Model.Game.Elements.Collectibles.Collectible;
import Fallbound.Model.Game.Elements.Collectibles.CollectibleFactory;
import Fallbound.Model.Game.Elements.Enemies.*;
import Fallbound.Model.Sound.SoundOption;
import Fallbound.Model.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class Scene {
    private final int width;
    private final int height;
    private final long startTime;
    private final List<Element> coins = new ArrayList<>();
    private final List<Element> enemies = new ArrayList<>();
    private final Player player = new Player(new Vector(19, 19), this);
    private final List<Element> walls = new ArrayList<>();
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Collectible> collectibles = new ArrayList<>();
    private int cameraOffset = 0;
    private long totalPausedTime = 0;
    private long pauseStartTime = 0;
    private boolean isPaused = false;

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.startTime = System.currentTimeMillis();
        buildInitialPlatforms();
    }

    private void buildInitialPlatforms() {
        buildWallBlock(0, 20, 38, 2);
        buildWallBlock(51, 20, 39, 2);
        buildWallBlock(36, 19, 2, 1);
        buildWallBlock(51, 19, 2, 1);
    }

    public void handleBullets() {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.setPosition(bullet.getPosition().add(new Vector(0, 1)));
            if (bullet.getPosition().getY() < 0 || bullet.getPosition().getY() >= getHeight() + getCameraOffset()) {
                iterator.remove();
            }
            handleBulletCollisions(iterator, bullet);
        }
    }

    private void handleBulletCollisions(Iterator<Bullet> iterator, Bullet bullet) {
        handleWallCollisions(iterator, bullet);
        handleEnemyCollisions(iterator, bullet);
        handleCollectibleCollisions(iterator, bullet);
    }

    private void handleWallCollisions(Iterator<Bullet> iterator, Bullet bullet) {
        Iterator<Element> wallIterator = walls.iterator();
        while (wallIterator.hasNext()) {
            Element wall = wallIterator.next();
            if (isColliding(bullet.getPosition(), wall.getPosition().subtract(new Vector(0, cameraOffset)))) {
                if (wall instanceof BreakableWall) {
                    wallIterator.remove();
                }
                iterator.remove();
                break;
            }
        }
    }

    private void handleEnemyCollisions(Iterator<Bullet> iterator, Bullet bullet) {
        for (Element element : enemies) {
            Enemy enemy = (Enemy) element;
            if (isColliding(bullet.getPosition(), enemy.getPosition().subtract(new Vector(0, cameraOffset)))) {
                if (enemy instanceof Shootable) {
                    SoundController.getInstance().playSound(SoundOption.ENEMY_DEATH);
                    removeEnemy(enemy);
                } else {
                    SoundController.getInstance().playSound(SoundOption.DING);
                }
                iterator.remove();
                break;
            }
        }
    }

    private void handleCollectibleCollisions(Iterator<Bullet> iterator, Bullet bullet) {
        for (Collectible collectible : collectibles) {
            if (isColliding(bullet.getPosition(), collectible.getPosition().subtract(new Vector(0, cameraOffset))) && collectible.getCost() <= player.getCollectedCoins()) {
                collectible.onCollect(player);
                SoundController.getInstance().playSound(SoundOption.COLLECTIBLE);
                player.setCollectedCoins(player.getCollectedCoins() - collectible.getCost());
                collectibles.remove(collectible);
                iterator.remove();
                break;
            }
        }
    }

    public boolean isColliding(Vector position1, Vector position2) {
        return round((float) position1.getX()) == round((float) position2.getX()) && round((float) position1.getY()) == round((float) position2.getY());
    }

    public void updateEnemies() {
        for (Element enemy : enemies) {
            ((Enemy) enemy).move();
        }
    }

    public void updateCameraOffset() {
        int playerY = player.getPosition().toPosition().getY();
        if (cameraOffset < playerY - (height / 2)) {
            cameraOffset = playerY - (height / 2);
            unloadElements(walls, cameraOffset);
            unloadElements(coins, cameraOffset);
            unloadElements(enemies, cameraOffset);
            generatePlatforms(cameraOffset);
        }
    }

    private void generatePlatforms(int cameraOffset) {
        int platformSpacing = 15;
        int platformOffset = 40;

        if (cameraOffset % platformSpacing == 0) {
            if (cameraOffset / platformSpacing % 12 == 0) {
                buildShopPlatform(cameraOffset + platformOffset);
            } else {
                buildRandomPlatform(cameraOffset + platformOffset);
            }
        }
    }

    private void unloadElements(List<Element> elements, int cameraOffset) {
        elements.removeIf(e -> e.getPosition().toPosition().getY() < cameraOffset - 10);
    }

    private int calculateNumberOfEnemies(long elapsedTime, int interval) {
        return (int) (elapsedTime / interval);
    }

    public void buildRandomPlatform(int y) {
        final int PLATFORM_OFFSET_MAX = 30;
        final int PLATFORM_WIDTH = 25;
        final int PLATFORM_HEIGHT = 4;
        final int PLATFORM_GAP = 40;

        int platformOffset = (int) (Math.random() * PLATFORM_OFFSET_MAX - PLATFORM_OFFSET_MAX / 2.0);

        int leftPlatformWidth = PLATFORM_WIDTH + platformOffset;
        buildWallBlock(0, y, leftPlatformWidth, PLATFORM_HEIGHT);

        int rightPlatformX = PLATFORM_GAP + leftPlatformWidth;
        int rightPlatformWidth = PLATFORM_WIDTH - platformOffset;
        buildWallBlock(rightPlatformX, y, rightPlatformWidth, PLATFORM_HEIGHT);

        final int SMALL_PLATFORM_OFFSET_Y = 10;
        final int MIN_SMALL_PLATFORM_WIDTH = 10;
        final int MAX_SMALL_PLATFORM_WIDTH = 20;
        final int MIN_FIRST_PLATFORM_OFFSET_X = 2;
        final int MAX_FIRST_PLATFORM_OFFSET_X = 5;

        int firstPlatformWidth = (int) (Math.random() * (MAX_SMALL_PLATFORM_WIDTH - MIN_SMALL_PLATFORM_WIDTH)) + MIN_SMALL_PLATFORM_WIDTH;
        int firstPlatformOffsetX = (int) (Math.random() * (MAX_FIRST_PLATFORM_OFFSET_X - MIN_FIRST_PLATFORM_OFFSET_X)) + MIN_FIRST_PLATFORM_OFFSET_X;

        int firstPlatformY = (int) (y + Math.random() * SMALL_PLATFORM_OFFSET_Y - SMALL_PLATFORM_OFFSET_Y / 2.0 + (double) PLATFORM_HEIGHT / 2);

        int firstPlatformX = PLATFORM_WIDTH + platformOffset + firstPlatformOffsetX + 1;
        buildBreakableWallBlock(firstPlatformX, firstPlatformY, firstPlatformWidth);

        int remainingWidth = (int) (PLATFORM_GAP - firstPlatformWidth - firstPlatformOffsetX - 5 - Math.random() * 3);
        int secondPlatformX = firstPlatformX + firstPlatformWidth + 2;
        int secondPlatformY = (int) (y + Math.random() * SMALL_PLATFORM_OFFSET_Y - SMALL_PLATFORM_OFFSET_Y / 2.0 + (double) PLATFORM_HEIGHT / 2);
        buildBreakableWallBlock(secondPlatformX, secondPlatformY, remainingWidth);

        long elapsedTime = getCurrentTime();

        int numNormalEnemies = 1 + calculateNumberOfEnemies(elapsedTime, 60000);
        int numShellEnemies = calculateNumberOfEnemies(elapsedTime, 120000);
        int numSpikeEnemies = calculateNumberOfEnemies(elapsedTime, 90000);

        for (int i = 0; i < numNormalEnemies; i++) {
            addNormalEnemy((int) (random() * leftPlatformWidth), (int) (y - random() * 3));
            addNormalEnemy((int) (random() * rightPlatformWidth + rightPlatformX), (int) (y - random() * 3));
        }

        for (int i = 0; i < numShellEnemies; i++) {
            addShellEnemy((int) (random() * leftPlatformWidth), y - 1);
            addShellEnemy((int) (random() * rightPlatformWidth + rightPlatformX), y - 1);
        }

        for (int i = 0; i < numSpikeEnemies; i++) {
            addSpikeEnemy((int) (random() * leftPlatformWidth), (int) (y - random() * 3));
            addSpikeEnemy((int) (random() * rightPlatformWidth + rightPlatformX), (int) (y - random() * 3));
        }
    }

    public void buildShopPlatform(int y) {
        final int PLATFORM_WIDTH = 30;
        final int PLATFORM_HEIGHT = 4;
        final int PLATFORM_GAP = 29;

        buildWallBlock(0, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);
        int rightPlatformX = PLATFORM_GAP + PLATFORM_WIDTH + 1;
        buildWallBlock(rightPlatformX, y, PLATFORM_WIDTH, PLATFORM_HEIGHT);

        Vector basePosition = new Vector(rightPlatformX + 5, y - 1);
        List<Collectible> shopCollectibles = CollectibleFactory.getRandomCollectibles(basePosition, this);

        for (int i = 0; i < shopCollectibles.size(); i++) {
            Collectible collectible = shopCollectibles.get(i);
            collectible.setPosition(basePosition.add(new Vector(i * 5, 0)));
            addCollectible(collectible);
        }
    }

    private void buildWallBlock(int x, int y, int w, int h) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                walls.add(new Wall(new Vector(x + i, y + j)));
            }
        }
    }

    private void buildBreakableWallBlock(int x, int y, int w) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < 3; j++) {
                walls.add(new BreakableWall(new Vector(x + i, y + j)));
            }
        }
    }

    public List<Collectible> getCollectibles() {
        return collectibles;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public int getCameraOffset() {
        return cameraOffset;
    }

    public void setPaused(boolean paused) {
        if (paused) {
            pauseStartTime = System.currentTimeMillis();
        } else {
            totalPausedTime += System.currentTimeMillis() - pauseStartTime;
        }
        isPaused = paused;
    }

    public long getCurrentTime() {
        return isPaused ? pauseStartTime - startTime - totalPausedTime : System.currentTimeMillis() - startTime - totalPausedTime;
    }

    public List<Element> getCoins() {
        return coins;
    }

    public void removeCoin(Coin coin) {
        coins.remove(coin);
    }

    public List<Element> getWalls() {
        return walls;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Element> getEnemies() {
        return enemies;
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
        coins.add(new Coin(enemy.getPosition()));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addCollectible(Collectible collectible) {
        collectibles.add(collectible);
    }

    public void addNormalEnemy(int x, int y) {
        enemies.add(new NormalEnemy(new Vector(x, y), this));
    }

    public void addShellEnemy(int x, int y) {
        enemies.add(new ShellEnemy(new Vector(x, y), this));
    }

    public void addSpikeEnemy(int x, int y) {
        enemies.add(new SpikeEnemy(new Vector(x, y), this));
    }

    public String timeToString(long time) {
        long minutes = time / 60000;
        long seconds = (time % 60000) / 1000;
        long milliseconds = (time % 1000) / 10;
        return String.format("%02d:%02d.%02d", minutes, seconds, milliseconds);
    }
}