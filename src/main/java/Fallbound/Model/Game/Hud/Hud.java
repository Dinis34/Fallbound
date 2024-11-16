package Fallbound.Model.Game.Hud;

import Fallbound.Model.Position;

public class Hud {
    private Position position;
    public Hud(Position position) {
        this.position = position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
