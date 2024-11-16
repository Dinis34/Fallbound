package Fallbound.Model.Game.Hud;
import Fallbound.Model.Position;

public class HighScore extends Hud{
    private int score;
    public HighScore(Position position, int score){super(position);
    this.score = score;}

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
