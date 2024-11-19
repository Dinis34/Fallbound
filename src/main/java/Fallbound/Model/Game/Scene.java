package Fallbound.Model.Game;

import Fallbound.Model.Game.Elements.Coin;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Elements.Tiles.Tile;
import Fallbound.Model.Game.Elements.Tiles.Wall;
import Fallbound.Model.Vector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

public class Scene {

    private final int width;
    private final int height;
    private final List<Coin> coins = new ArrayList<>();
    private Player player = new Player(new Vector(20, 15), this);
    private List<Tile> walls = new ArrayList<>();
    private long startTime;

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.startTime = System.currentTimeMillis();

        buildWallBlock(10, 20, 68, 3);
        buildWallBlock(50, 17, 12, 1);
        buildWallBlock(30, 14, 12, 1);

        buildCoinBlock(13, 9, 1, 9);
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

    public List<Coin> getCoins() {
        return coins;
    }

    public void removeCoin(Coin coin) {
        this.coins.remove(coin);
    }

    public List<Tile> getWalls() {
        return walls;
    }

    public void setWalls(List<Tile> walls) {
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
