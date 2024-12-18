package Fallbound.Model.Game.Elements.Enemies;

import Fallbound.Controller.Game.Elements.FloatingEnemyController;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

public class SpikeEnemy extends Enemy implements Floating, Shootable {
    public SpikeEnemy(Vector position, Scene scene) {
        super(position, scene);
        setController(new FloatingEnemyController(this, scene, 500));
    }
}
