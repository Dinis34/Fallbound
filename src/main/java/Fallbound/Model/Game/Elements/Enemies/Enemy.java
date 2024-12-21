package Fallbound.Model.Game.Elements.Enemies;

import Fallbound.Controller.Game.Elements.EnemyController;
import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public abstract class Enemy extends Element {
    private final Scene scene;
    private EnemyController controller;

    public Enemy(Vector position, Scene scene) {
        super(position);
        this.scene = scene;
    }

    public EnemyController getController() {
        return controller;
    }

    public void setController(EnemyController controller) {
        this.controller = controller;
    }

    public Scene getScene() {
        return scene;
    }

    public void move() {
        if (controller != null) {
            controller.move();
        }
    }
}

