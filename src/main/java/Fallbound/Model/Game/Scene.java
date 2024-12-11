package Fallbound.Model.Game;

import Fallbound.Model.Game.Elements.Coin;
import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Elements.Wall;
import Fallbound.Model.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class Scene {

    private final int width;
    private final int height;
    private final long startTime;
    private final List<Element> coins = new ArrayList<>();
    private Player player = new Player(new Vector(19, 19), this);
    private List<Element> walls = new ArrayList<>();
    private int cameraOffset = 0;

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.startTime = System.currentTimeMillis();

        // first platform
        buildWallBlock(0, 20, 38, 2);
        buildWallBlock(51, 20, 38, 2);
        buildWallBlock(36, 19, 2, 1);
        buildWallBlock(51, 19, 2, 1);
    }

    public int getCameraOffset() {
        return cameraOffset;
    }

    public void buildRandomPlatform(int y) {
        int platformOffsetMax = 30;
        int platformWidth = 25;
        int platformHeight = 4;
        int platformGap = 40;

        int platformOffset = (int) ((Math.random() * platformOffsetMax) - ((double) platformOffsetMax / 2));

        // build edge platforms
        buildWallBlock(0, y, platformWidth + platformOffset, platformHeight);
        buildWallBlock(platformGap + platformWidth + platformOffset, y, platformWidth - platformOffset, platformHeight);

        // build small middle platforms
        int smallPlatformHeight = 3;
        int smallPlatformOffsetY = 6; // (from -3 to 3)
        int firstPlatformWidth = (int) (random() * 10) + 10; // (from 10 to 20)
        int firstPlatformOffsetX = (int) (random() * 3) +2; // (from 0 to 3)
        buildWallBlock(firstPlatformOffsetX + platformWidth + platformOffset + 1, (int) (y + (random()*smallPlatformOffsetY - (double) smallPlatformOffsetY /2) + (double) platformHeight /2), firstPlatformWidth, smallPlatformHeight);
        buildWallBlock(2 +firstPlatformOffsetX + platformWidth + platformOffset + 1 + firstPlatformWidth, (int) (y + (random()*smallPlatformOffsetY - (double) smallPlatformOffsetY /2) + (double) platformHeight /2), (int) (platformGap - firstPlatformWidth - 4 - firstPlatformOffsetX - random()*3), smallPlatformHeight);
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
        System.out.println("number of walls: " + getWalls().size());
    }

    public long getStartTime() {
        return startTime;
    }

    public String timeToString(long time) {
        long minutes = time / 60000;
        long seconds = (time % 60000) / 1000;
        long milliseconds = (time % 1000) / 10;
        return String.format("%02d:%02d.%02d", minutes, seconds, milliseconds);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    private void buildCoinBlock(int x, int y, int w, int h) {
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.coins.add(new Coin(new Vector(x + i, y + j)));
            }
        }
    }

    public boolean isColliding(Vector position1, Vector position2) {
        return round((float) position1.getX()) == round((float) position2.getX()) && round((float) position1.getY()) == round((float) position2.getY());
    }
}
