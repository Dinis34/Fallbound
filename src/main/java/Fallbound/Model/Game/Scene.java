package Fallbound.Model.Game;

import Fallbound.Model.Game.Elements.Wall;
import Fallbound.Model.Position;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private int width;
    private int height;
    private int score;

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    private List<Wall> walls = new ArrayList<>();

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
                this.walls.add(new Wall(new Position(x + i, y + j)));
            }
        }
    }

    private void buildWalls() {

    }
}
