package Fallbound.Controller.Menu;

import Fallbound.Controller.Controller;
import Fallbound.Game;
import Fallbound.Model.Menu.StartMenu;
import Fallbound.State.GameState;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class StartMenuController extends Controller<StartMenu> {

    public StartMenuController(StartMenu menu) {
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
                if (getModel().isSelectedPlay()) {
                    game.setState(GameState.NEW_GAME);
                } else if (getModel().isSelectedExit()) {
                    game.setState(GameState.QUIT_GAME);
                }
                break;
            default:
        }
    }
}
