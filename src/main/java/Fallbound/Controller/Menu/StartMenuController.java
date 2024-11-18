package Fallbound.Controller.Menu;

import Fallbound.Game;
import Fallbound.Model.Menu.StartMenu;
import Fallbound.State.GameState;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

public class StartMenuController extends MenuController<StartMenu> {

    public StartMenuController(StartMenu menu) {
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
            if (getModel().isSelectedPlay()) {
                game.setState(GameState.NEW_GAME);
            } else if (getModel().isSelectedExit()) {
                game.setState(GameState.QUIT_GAME);
            }
        }
    }
}
