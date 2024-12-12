package Fallbound.Controller.Game.Elements;

import Fallbound.Controller.Controller;
import Fallbound.Game;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Game.Elements.Enemies.FloatingEnemy;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

public class PlayerController extends Controller<Player> {
    public PlayerController(Player player) {
        super(player);
    }

    @Override
    public void step(Game game, Set<Integer> keys, long time) throws IOException {
        if (keys.contains(KeyEvent.VK_SPACE)) {
            if (getModel().getOnGround()) {
                getModel().jump();
            } else {
                getModel().shoot();
            }
        }
        if (keys.contains(KeyEvent.VK_LEFT) || keys.contains(KeyEvent.VK_A)) {
            getModel().moveLeft();
        }
        if (keys.contains(KeyEvent.VK_RIGHT) || keys.contains(KeyEvent.VK_D)) {
            getModel().moveRight();
        }
        if (!keys.contains(KeyEvent.VK_LEFT) && !keys.contains(KeyEvent.VK_RIGHT) && !keys.contains(KeyEvent.VK_A) && !keys.contains(KeyEvent.VK_D)) {
            getModel().stop();
        }
        getModel().update();
        getModel().getScene().updateFloatingEnemies();
    }
}