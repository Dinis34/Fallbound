package Fallbound.Model.Game;

import Fallbound.Model.Game.Elements.Coin;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Elements.Wall;
import Fallbound.Model.Position;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private int width;
    private int height;
    private int score;
    private int coincount;

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

    public List<Coin> getCoins() {
        return coins;
    }

    public int getScore() {
        return score;
    }
    public int getCoincount() {
        return coincount;
    }
    private Player player = new Player(new Position(20, 20));
    private List<Wall> walls = new ArrayList<>();
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
                this.walls.add(new Wall(new Position(x + i, y + j)));
            }
        }
        this.coins.add(new Coin(new Position(2,  19)));// test para checkar se a coin aparece
        for(Coin a : this.coins) { // ns onde por este loop por isso vai ficar aqui ¯\_(ツ)_/¯
            if(a.checkcollision(this.player)){
                this.coincount++;
                this.coins.remove(a);
                break;
            }
        }
    }

    private void buildWalls() {

    }
}
