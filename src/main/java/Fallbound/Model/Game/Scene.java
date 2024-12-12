package Fallbound.Model.Game;

import Fallbound.Model.Game.Elements.Coin;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Elements.Tiles.Tile;
import Fallbound.Model.Game.Elements.Tiles.Wall;
import Fallbound.Model.Game.Elements.Enemies.FloatingEnemy;
import Fallbound.Model.Game.Elements.*;
import Fallbound.Model.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.round;

public class Scene {

    private final int width;
    private final int height;
    private final long startTime;
    private final List<Element> coins = new ArrayList<>();
    private Player player = new Player(new Vector(19, 19), this);
    private List<FloatingEnemy> floatingEnemies = new ArrayList<>();
    private List<Element> walls = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();
    private int cameraOffset = 0;
    private long totalPausedTime = 0;
    private long pauseStartTime = 0;
    private boolean isPaused = false;

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.startTime = System.currentTimeMillis();

        // first platform
        buildWallBlock(0, 20, 38, 2);
        buildWallBlock(51, 20, 38, 2);
        buildWallBlock(36, 19, 2, 1);
        buildWallBlock(51, 19, 2, 1);
      
        addFloatingEnemy(40, 5);
        addFloatingEnemy(50, 10);
        addFloatingEnemy(63, 19);
      
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void addBullet(Bullet bullet) {
        this.bullets.add(bullet);
    }

    public int getCameraOffset() {
        return cameraOffset;
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

        final int SMALL_PLATFORM_HEIGHT = 3;
        final int SMALL_PLATFORM_OFFSET_Y = 10;
        final int MIN_SMALL_PLATFORM_WIDTH = 10;
        final int MAX_SMALL_PLATFORM_WIDTH = 20;
        final int MIN_FIRST_PLATFORM_OFFSET_X = 2;
        final int MAX_FIRST_PLATFORM_OFFSET_X = 5;

        int firstPlatformWidth = (int) (Math.random() * (MAX_SMALL_PLATFORM_WIDTH - MIN_SMALL_PLATFORM_WIDTH)) + MIN_SMALL_PLATFORM_WIDTH;
        int firstPlatformOffsetX = (int) (Math.random() * (MAX_FIRST_PLATFORM_OFFSET_X - MIN_FIRST_PLATFORM_OFFSET_X)) + MIN_FIRST_PLATFORM_OFFSET_X;

        int firstPlatformY = (int) (y + Math.random() * SMALL_PLATFORM_OFFSET_Y - SMALL_PLATFORM_OFFSET_Y / 2.0 + (double) PLATFORM_HEIGHT / 2);

        int firstPlatformX = PLATFORM_WIDTH + platformOffset + firstPlatformOffsetX + 1;
        buildBreakableWallBlock(firstPlatformX, firstPlatformY, firstPlatformWidth, SMALL_PLATFORM_HEIGHT);

        int remainingWidth = (int) (PLATFORM_GAP - firstPlatformWidth - firstPlatformOffsetX - 5 - Math.random() * 3);
        int secondPlatformX = firstPlatformX + firstPlatformWidth + 2;
        int secondPlatformY = (int) (y + Math.random() * SMALL_PLATFORM_OFFSET_Y - SMALL_PLATFORM_OFFSET_Y / 2.0 + (double) PLATFORM_HEIGHT / 2);
        buildBreakableWallBlock(secondPlatformX, secondPlatformY, remainingWidth, SMALL_PLATFORM_HEIGHT);
    }

    public void updateCameraOffset() {
        int playerY = getPlayer().getPosition().toPosition().getY();
        if (cameraOffset < playerY - (getHeight() / 2)) {
            cameraOffset = playerY - (getHeight() / 2);
            unloadElements(getWalls(), cameraOffset);
            unloadElements(getCoins(), cameraOffset);

            generatePlatforms(cameraOffset);
        }
    }

    private void generatePlatforms(int cameraOffset) {
        int platformSpacing = 15;
        int platformOffset = 40;

        if (cameraOffset % platformSpacing == 0) {
            buildRandomPlatform(cameraOffset + platformOffset);
        }
    }

    private void unloadElements(List<Element> elements, int cameraOffset) {
        elements.removeIf(e -> e.getPosition().toPosition().getY() < cameraOffset - 10);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getStartTime() {
        return startTime;
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
        if (isPaused) {
            return pauseStartTime - startTime - totalPausedTime;
        } else {
            return System.currentTimeMillis() - startTime - totalPausedTime;
        }
    }

    public List<Element> getCoins() {
        return coins;
    }

    public void removeCoin(Coin coin) {
        this.coins.remove(coin);
    }

    public List<Element> getWalls() {
        return walls;
    }

    public void setWalls(List<Element> walls) {
        this.walls = walls;
    }

    public Player getPlayer() {
        return player;
    }

    public List<FloatingEnemy> getFloatingEnemies() {
        return floatingEnemies;
    }

    public void removeFloatingEnemy(FloatingEnemy floatingEnemy) {
        this.floatingEnemies.remove(floatingEnemy);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void buildWallBlock(int x, int y, int w, int h) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.walls.add(new Wall(new Vector(x + i, y + j)));
            }
        }
    }

    private void buildBreakableWallBlock(int x, int y, int w, int h) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.walls.add(new BreakableWall(new Vector(x + i, y + j)));
            }
        }
    }

    private void buildCoinBlock(int x, int y, int w, int h) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.coins.add(new Coin(new Vector(x + i, y + j)));
            }
        }
    }

    public void addFloatingEnemy(int x, int y) {
        floatingEnemies.add(new FloatingEnemy(new Vector(x, y), this));
    }

    public String timeToString(long time) {
        long minutes = time / 60000;
        long seconds = (time % 60000) / 1000;
        long milliseconds = (time % 1000) / 10;
        return String.format("%02d:%02d.%02d", minutes, seconds, milliseconds);
    }

    public void handleBullets() {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.setPosition(bullet.getPosition().add(new Vector(0, 1)));
            if (bullet.getPosition().getY() < 0 || bullet.getPosition().getY() >= getHeight() + getCameraOffset()) {
                iterator.remove();
            }
            Iterator<Element> wallIterator = getWalls().iterator();
            while (wallIterator.hasNext()) {
                Element wall = wallIterator.next();
                if (isColliding(bullet.getPosition(), wall.getPosition().subtract(new Vector(0, getCameraOffset())))) {
                    if (wall instanceof BreakableWall) {
                        wallIterator.remove();
                    }
                    iterator.remove();
                    break;
                }
            }
        }
    }

    public boolean isColliding(Vector position1, Vector position2) {
        return round((float) position1.getX()) == round((float) position2.getX()) && round((float) position1.getY()) == round((float) position2.getY());
    }

    public boolean isCollidingFromAbove(Vector position1, Vector position2) {
        return round((float) position1.getY()) == round((float) position2.getY() + 1) && round((float) position1.getX()) == round((float) position2.getX());
    }

    public void updateFloatingEnemies() {
        for (FloatingEnemy floatingEnemy : floatingEnemies) {
            floatingEnemy.followPlayer();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
