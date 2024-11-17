package Fallbound.Model.Game;

import Fallbound.GUI.GUI;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Elements.Wall;
import Fallbound.Model.Game.Hud.CoinDisplay;
import Fallbound.Model.Game.Hud.HighScore;
import Fallbound.Model.Game.Hud.Time;
import Fallbound.Model.Position;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private int width;
    private int height;
    private int score;
    private int coincount;
    private HighScore highScore;
    private Time time;  //placeholder até fazermos o timer direitinho
    private CoinDisplay coinDisplay;

    public CoinDisplay getCoinDisplay() {return coinDisplay;}
    public Time getTime() {
        return time;
    }
    public HighScore getHighScore() {
        return highScore;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Player player = new Player(new Position(20, 20));
    private List<Wall> walls = new ArrayList<>();

    public Scene(int width, int height) {
        this.width = width;
        this.height = height;
        this.score = 0;
        this.coincount = 0;

        int x = 10;
        int y = 20;
        int w = 68;
        int h = 2;

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.walls.add(new Wall(new Position(x + i, y + j)));
            }
        }
        this.highScore = new HighScore(new Position(1,2), score);
        this.time = new Time(new Position(1, 1), 0); // placeholder
        this.coinDisplay = new CoinDisplay(new Position(70, 1), 0);

    }

    private void buildWalls() {

    }
}
