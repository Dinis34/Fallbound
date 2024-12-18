package Fallbound.Controller.Game;

import Fallbound.Controller.Controller;
import Fallbound.Controller.Game.Elements.PlayerController;
import Fallbound.Game;
import Fallbound.Model.Game.Elements.Element;
import Fallbound.Model.Game.Elements.Enemies.Enemy;
import Fallbound.Model.Game.Scene;
import Fallbound.State.GameState;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

public class SceneController extends Controller<Scene> {

    private final PlayerController playerController = new PlayerController(getModel().getPlayer());

    public SceneController(Scene model) {
        super(model);
    }

    @Override
    public void step(Game game, Set<Integer> keys, long time) throws IOException {
        playerController.step(game, keys, time);
        for (Element enemy : getModel().getEnemies()) {
            ((Enemy) enemy).move();
        }
        if (keys.contains(KeyEvent.VK_ESCAPE)) {
            game.setState(GameState.PAUSE_MENU);
        }
    }
}