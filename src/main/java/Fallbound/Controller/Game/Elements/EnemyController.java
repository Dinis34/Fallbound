package Fallbound.Controller.Game.Elements;

import Fallbound.Model.Game.Elements.Enemies.Enemy;
import Fallbound.Model.Game.Scene;

public abstract class EnemyController {
    protected Enemy enemy;
    protected Scene scene;

    public EnemyController(Enemy enemy, Scene scene) {
        this.enemy = enemy;
        this.scene = scene;
    }

    public abstract void move();
}
