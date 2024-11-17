package Fallbound.Model.Game;

import Fallbound.Model.Game.Elements.Coin;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Elements.Tiles.Tile;
import Fallbound.Model.Game.Elements.Tiles.Wall;
import static java.lang.Math.round;
import Fallbound.Model.Vector;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final int width;
    private final int height;
    private final int score;
    private int coincount;
    private Player player = new Player(new Vector(20, 15), this);
    private List<Tile> walls = new ArrayList<>();
    
    public List<Coin> getCoins() {
        return coins;
    }

    public int getScore() {
        return score;
    }
  
    public int getCoincount() {
        return coincount;
    }

    private List<Coin> coins = new ArrayList<>();
    
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
                this.walls.add(new Wall(new Vector(x + i, y + j)));
            }
        }
        this.coins.add(new Coin(new Position(15,  19)));// test para checkar se a coin aparece
        for(Coin a : this.coins) { // ns onde por este loop por isso vai ficar aqui ¯\_(ツ)_/¯
            if(a.checkcollision(this.player)){
                this.coincount++;
                this.coins.remove(a);
                break;
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
