package Fallbound.Controller.Menu;

import Fallbound.Controller.Controller;
import Fallbound.Game;
import Fallbound.Model.Menu.PauseMenu;
import Fallbound.State.GameState;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class PauseMenuController extends Controller<PauseMenu> {

    public PauseMenuController(PauseMenu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, KeyStroke key, long time) throws IOException {
        if (key == null) {
            return;
        }
        switch (key.getKeyType()) {
            case ArrowUp:
                getModel().previousOption();
                break;
            case ArrowDown:
                getModel().nextOption();
                break;
            case Enter:
                if (getModel().isSelectedContinue()) {
                    game.setState(GameState.RESUME_GAME);
                } else if (getModel().isSelectedRestart()) {
                    game.setState(GameState.NEW_GAME);
                } else if (getModel().isSelectedExit()) {
                    game.setState(GameState.START_MENU);
                }
                break;
            default:
            }
    }
}

