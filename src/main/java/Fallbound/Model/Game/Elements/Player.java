package Fallbound.Model.Game.Elements;

import Fallbound.Model.Game.Scene;
import Fallbound.Model.Position;
import Fallbound.Model.Vector;

public class Player extends Element {

    private Vector velocity;
    private final Vector maxVelocity;
    private final double acceleration;
    private final double jumpBoost;
    private Scene scene;

    public Player(Position position) {
        super(position);
        this.velocity = new Vector(0, 0);
        this.maxVelocity = new Vector(2.0, 3.0);
        this.acceleration = 0.75;
        this.jumpBoost = 3.6;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Vector getMaxVelocity() {
        return maxVelocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getJumpBoost() {
        return jumpBoost;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
