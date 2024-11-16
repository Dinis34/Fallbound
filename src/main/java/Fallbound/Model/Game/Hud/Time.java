package Fallbound.Model.Game.Hud;

import Fallbound.Model.Position;

public class Time extends Hud{
    private int time;
    public Time(Position posistion, int time) {
        super(posistion);
        this.time = time;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
} // placeholders pq ainda n temos o timer feito
