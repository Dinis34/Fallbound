package Fallbound.Model.Game;

import Fallbound.Model.Game.Elements.Coin;
import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Elements.Wall;
import Fallbound.Model.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class Scene {

    private final int width;
    private final int height;
    private final long startTime;
    private Player player = new Player(new Vector(19, 19), this);
    private final List<Element> coins = new ArrayList<>();
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

        buildRandomPlatform(30);
        buildRandomPlatform(40);
    }

    public int getCameraOffset() {
        return cameraOffset;
    }

    public void buildRandomPlatform(int y) {
        int platformOffsetMax = 30;
        int platformWidth = 25;
        int platformHeight = 4;

        int platformOffset = (int) ((Math.random() * platformOffsetMax) - ((double) platformOffsetMax / 2));

        buildWallBlock(0, y, platformWidth + platformOffset, platformHeight);
        buildWallBlock(65 + platformOffset, y, platformWidth - platformOffset, platformHeight);
    }

    public void updateCameraOffset() {
        int playerY = getPlayer().getPosition().toPosition().getY();
        if (cameraOffset < playerY - (getHeight() / 2)) {
            cameraOffset = playerY - (getHeight() / 2);
            unloadElements(getWalls(), cameraOffset);
            unloadElements(getCoins(), cameraOffset);
            tryCreatingPlatform(getWalls(), cameraOffset);
        }
    }

    private void tryCreatingPlatform(List<Element> elements, int cameraOffset) {
        if (cameraOffset < 10) {
            buildRandomPlatform((cameraOffset / 10) * 10);
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
