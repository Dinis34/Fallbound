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
            if (getModel().isOnGround()) {
                getModel().jump();
                getModel().getScene().updateFloatingEnemies();
            }
        }
        if (keys.contains(KeyEvent.VK_LEFT)) {
            getModel().moveLeft();
            getModel().getScene().updateFloatingEnemies();
        }
        if (keys.contains(KeyEvent.VK_RIGHT)) {
            getModel().moveRight();
            getModel().getScene().updateFloatingEnemies();
        }
        if (!keys.contains(KeyEvent.VK_LEFT) && !keys.contains(KeyEvent.VK_RIGHT)) {
            getModel().stop();
        }
        getModel().update();
    }
}