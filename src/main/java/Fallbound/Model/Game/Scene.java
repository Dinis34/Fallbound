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
    private final int score;
    private Player player = new Player(new Vector(20, 15), this);
    private List<Tile> walls = new ArrayList<>();
    private final List<Coin> coins = new ArrayList<>();

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.score = 0;

        buildWallBlock(10, 20, 68, 2);

        this.coins.add(new Coin(new Vector(15, 19)));

    }

    public List<Coin> getCoins() {
        return coins;
    }

    public int getScore() {
        return score;
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

    public void checkCoinCollision() {
        for (Coin coin : this.coins) {
            System.out.println("coin position: " + coin.getPosition().toPosition());
            System.out.println("player position: " + this.player.getPosition().toPosition());
            if (isColliding(coin.getPosition(), this.player.getPosition())) {
                System.out.println("Coin collected");
                this.coins.remove(coin);
                break;
            }
        }
    }

    public boolean isColliding(Vector position1, Vector position2) {
        return round((float) position1.getX()) == round((float) position2.getX()) && round((float) position1.getY()) == round((float) position2.getY());
    }
}
