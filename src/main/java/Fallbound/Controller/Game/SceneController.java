package Fallbound.Controller.Game;

import Fallbound.Controller.Controller;
import Fallbound.Game;
import Fallbound.Model.Game.Scene;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class SceneController extends Controller<Scene> {

    public SceneController(Scene model) {
        super(model);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        // checkCollisions();
            // - check if the player is colliding with any walls
            // - check if the player is colliding with any enemies
            // - check if the player is colliding with any collectibles
        // could be a controller for each one?
    }
}
