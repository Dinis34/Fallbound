package Fallbound.Model.Game;

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

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.score = 0;

        int x = 10;
        int y = 20;
        int w = 68;
        int h = 2;

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.walls.add(new Wall(new Vector(x + i, y + j)));
            }
        }
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

    private void buildWalls() {

    }

    public boolean isColliding(Vector position1, Vector position2) {
        return round(position1.getX()) == round(position2.getX()) && round(position1.getY()) == round(position2.getY());
    }
}
