package Fallbound.Model.Game.Elements.Enemies;

import Fallbound.Controller.Game.Elements.FloatingEnemyController;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class NormalEnemy extends Enemy implements Shootable, Stompable, Floating {
    public NormalEnemy(Vector position, Scene scene) {
        super(position, scene);
        setController(new FloatingEnemyController(this, scene, 300));
    }
}
