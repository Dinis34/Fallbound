package Fallbound.Controller.Menu;

import Fallbound.Game;
import Fallbound.Model.Menu.GameOverMenu;
import Fallbound.State.GameState;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

public class GameOverMenuController extends MenuController<GameOverMenu> {
    public GameOverMenuController(GameOverMenu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, Set<Integer> keys, long time) throws IOException {
        if (keys.contains(KeyEvent.VK_UP)) {
            getModel().previousOption();
        }
        if (keys.contains(KeyEvent.VK_DOWN)) {
            getModel().nextOption();
        }
        if (keys.contains(KeyEvent.VK_ENTER)) {
            if (getModel().isSelectedRestart()) {
                game.setState(GameState.NEW_GAME);
            } else if (getModel().isSelectedExit()) {
                game.setState(GameState.START_MENU);
            } else if (getModel().isSelectedExitToDesktop()) {
                game.setState(GameState.QUIT_GAME);
            }
        }
    }

}

