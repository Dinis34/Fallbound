package Fallbound.Controller.Game;

import Fallbound.Controller.Controller;
import Fallbound.Controller.Game.Elements.BulletController;
import Fallbound.Controller.Game.Elements.PlayerController;
import Fallbound.Game;
import Fallbound.Model.Game.Scene;
import Fallbound.State.GameState;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

public class SceneController extends Controller<Scene> {

    private final PlayerController playerController = new PlayerController(getModel().getPlayer());
    private final BulletController bulletController = new BulletController(getModel());

    public SceneController(Scene model) {
        super(model);
    }

    @Override
    public void step(Game game, Set<Integer> keys, long time) throws IOException {
        playerController.step(game, keys, time);
        bulletController.step(game, keys, time);
        if (keys.contains(KeyEvent.VK_ESCAPE)) {
            game.setState(GameState.PAUSE_MENU);
        }

        if (keys.contains(KeyEvent.VK_Q)) {
            game.setState(GameState.GAME_OVER); // q for now
        }
    }
}