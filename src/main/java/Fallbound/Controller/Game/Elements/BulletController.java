package Fallbound.Controller.Game.Elements;

import Fallbound.Controller.Controller;
import Fallbound.Game;
import Fallbound.Model.Game.Elements.Bullet;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Vector;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class BulletController extends Controller<Scene> {
    public BulletController(Scene model) {
        super(model);
    }

    @Override
    public void step(Game game, Set<Integer> keys, long time) throws IOException {
        List<Bullet> bullets = getModel().getBullets();
        bullets.forEach(bullet -> bullet.setPosition(bullet.getPosition().add(new Vector(0, 1))));
    }
}
