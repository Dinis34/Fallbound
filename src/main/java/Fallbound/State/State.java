package Fallbound.State;

import Fallbound.Controller.Controller;
import Fallbound.Controller.Game.SceneController;
import Fallbound.Controller.Menu.StartMenuController;
import Fallbound.GUI.GUI;
import Fallbound.Game;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Menu.StartMenu;
import Fallbound.View.Game.SceneViewer;
import Fallbound.View.Menu.StartMenuViewer;
import Fallbound.View.Viewer;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;

public class State {
    public static State instance;
    private GameState currentState;
    private GameState previousState;
    private Controller controller;
    private Viewer viewer;
    private Scene scene;

    private State() {
        currentState = GameState.START_MENU;
        previousState = GameState.START_MENU;
    }

    public static State getInstance() {
        if (instance == null) {
            instance = new State();
        }
        return instance;
    }

    public void UpdateState(GameState newState) throws IOException {
        if (newState == GameState.START_MENU) {
            previousState = GameState.START_MENU;
        } else {
            previousState = currentState;
        }
        currentState = newState;
        StateActions();
    }

    public void UpdateToPrevious() throws IOException {
        GameState aux = currentState;
        currentState = previousState;
        previousState = aux;
        StateActions();
    }

    public void step(GUI gui, Game game, long time) throws IOException {
        if (controller == null || viewer == null) {
            StateActions();
        }
        KeyStroke key = gui.getNextAction();
        controller.step(game, key, time);
        viewer.draw(gui, time);
    }

    public void StateActions() throws IOException {
        switch (currentState) {
            case START_MENU:
                StartMenu startMenu = new StartMenu();
                this.controller = new StartMenuController(startMenu);
                this.viewer = new StartMenuViewer(startMenu);
                break;

            case NEW_GAME:
                scene = new Scene(90,30);
                this.controller = new SceneController(scene);
                this.viewer = new SceneViewer(scene);
                break;

            case GAME_OVER:
//                todo:
//                  - GameOverMenu class (model)
//                  - GameOverMenuController class (controller)
//                  - GameOverMenuViewer class (view)
                break;

            case QUIT_GAME:
        }
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public GameState getPreviousState() {
        return previousState;
    }

    public void setPreviousState(GameState previousState) {
        this.previousState = previousState;
    }
}
