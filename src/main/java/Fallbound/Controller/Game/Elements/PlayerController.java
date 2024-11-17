package Fallbound.Controller.Game.Elements;

import Fallbound.Controller.Controller;
import Fallbound.Game;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Position;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

public class PlayerController extends Controller<Player> {
    public PlayerController(Player player) {
        super(player);
    }

    @Override
    public void step(Game game, Set<Integer> keys, long time) throws IOException {
        if (keys.contains(KeyEvent.VK_UP)) {
            System.out.println("Up");
        }
        if (keys.contains(KeyEvent.VK_DOWN)) {
            System.out.println("Down");
        }
        if (keys.contains(KeyEvent.VK_LEFT)) {
            getModel().setPosition(new Position(getModel().getPosition().getX() - 1, getModel().getPosition().getY()));
        }
        if (keys.contains(KeyEvent.VK_RIGHT)) {
            getModel().setPosition(new Position(getModel().getPosition().getX() + 1, getModel().getPosition().getY()));
        }
    }
}