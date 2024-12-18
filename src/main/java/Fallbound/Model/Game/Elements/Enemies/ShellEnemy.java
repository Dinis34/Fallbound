package Fallbound.Model.Game.Elements.Enemies;

import Fallbound.Controller.Game.Elements.GroundedEnemyController;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class ShellEnemy extends Enemy implements Stompable {
    public ShellEnemy(Vector position, Scene scene) {
        super(position, scene);
        setController(new GroundedEnemyController(this, scene));
    }
}
