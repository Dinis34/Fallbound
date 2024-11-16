package Fallbound.Controller.Game.Elements;

import Fallbound.Controller.Controller;
import Fallbound.Game;
import Fallbound.Model.Game.Elements.Player;
import Fallbound.Model.Position;
import Fallbound.Model.Vector;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class PlayerController extends Controller<Player> {
    public PlayerController(Player player) {
        super(player);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if (key != null) {
            switch (key.getKeyType()) {
                case ArrowUp:
                    System.out.println("Up");
                    break;
                case ArrowDown:
                    System.out.println("Down");
                    break;
                case ArrowLeft:
                    getModel().setPosition(new Position(getModel().getPosition().getX() - 1, getModel().getPosition().getY()));
                    break;
                case ArrowRight:
                    getModel().setPosition(new Position(getModel().getPosition().getX() + 1, getModel().getPosition().getY()));
                    break;
                default:
            }
        }

    }

    public void move(Position position) {
    }
}
