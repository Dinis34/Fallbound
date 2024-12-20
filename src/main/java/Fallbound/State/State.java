package Fallbound.State;

import Fallbound.Controller.Controller;
import Fallbound.Controller.Game.SceneController;
import Fallbound.Controller.Menu.GameOverMenuController;
import Fallbound.Controller.Menu.MenuController;
import Fallbound.Controller.Menu.PauseMenuController;
import Fallbound.Controller.Menu.StartMenuController;
import Fallbound.Controller.Sound.SoundController;
import Fallbound.GUI.GUI;
import Fallbound.Game;
import Fallbound.Model.Game.Scene;
import Fallbound.Model.Menu.GameOverMenu;
import Fallbound.Model.Menu.PauseMenu;
import Fallbound.Model.Menu.StartMenu;
import Fallbound.Model.Sound.SoundOption;
import Fallbound.View.Game.SceneViewer;
import Fallbound.View.Menu.GameOverMenuViewer;
import Fallbound.View.Menu.PauseMenuViewer;
import Fallbound.View.Menu.StartMenuViewer;
import Fallbound.View.Viewer;

import java.io.IOException;
import java.util.Set;

public class State {
    public static State instance;
    private GameState currentState;
    private GameState previousState;
    private Controller<?> controller;
    private Viewer<?> viewer;
    private Scene scene;

    private SceneController sceneController;

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

    public void updateState(GameState newState) {
        if (newState == GameState.PAUSE_MENU) {
            scene.setPaused(true);
        } else if (currentState == GameState.PAUSE_MENU) {
            scene.setPaused(false);
        }
        previousState = currentState;
        currentState = newState;
        stateActions();
    }


    public void updateToPrevious() {
        GameState aux = currentState;
        currentState = previousState;
        previousState = aux;
        stateActions();
    }

    public void step(GUI gui, Game game, long time) throws IOException {
        if (controller == null || viewer == null) {
            stateActions();
        }
        Set<Integer> keys = gui.getNextAction();
        if (controller instanceof MenuController) {
            keys = gui.getNextSingleAction();
        }
        controller.step(game, keys, time);
        viewer.draw(gui, time);
    }

    public void stateActions() {
        switch (currentState) {
            case START_MENU:
                StartMenu startMenu = new StartMenu();
                this.controller = new StartMenuController(startMenu);
                this.viewer = new StartMenuViewer(startMenu);
                SoundController.getInstance().playSound(SoundOption.MENU_MUSIC);
                break;
            case PAUSE_MENU:
                SoundController.getInstance().playSound(SoundOption.MENU_SELECT);
                SoundController.getInstance().stopSound(SoundOption.BACKGROUND_MUSIC);
                PauseMenu pauseMenu = new PauseMenu();
                this.controller = new PauseMenuController(pauseMenu);
                this.viewer = new PauseMenuViewer(pauseMenu);
                break;
            case NEW_GAME:
                SoundController.getInstance().stopAllSounds();
                SoundController.getInstance().playSound(SoundOption.MENU_SELECT);
                scene = new Scene(90, 30);
                controller = new SceneController(scene);
                this.sceneController = (SceneController) controller;
                this.viewer = new SceneViewer(scene);
                SoundController.getInstance().playSound(SoundOption.BACKGROUND_MUSIC);
                break;
            case RESUME_GAME:
                SoundController.getInstance().playSound(SoundOption.MENU_SELECT);
                SoundController.getInstance().resumePlayingMusic();
                controller = sceneController;
                viewer = new SceneViewer(scene);
                break;
            case GAME_OVER:
                SoundController.getInstance().stopAllSounds();
                SoundController.getInstance().playSound(SoundOption.PLAYER_DEATH);
                GameOverMenu gameOverMenu = new GameOverMenu();
                this.controller = new GameOverMenuController(gameOverMenu, (int) scene.getCurrentTime());
                this.viewer = new GameOverMenuViewer(gameOverMenu);
                break;
            case QUIT_GAME:
                SoundController.getInstance().stopAllSounds();
                SoundController.getInstance().playSound(SoundOption.MENU_SELECT);
                break;
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

    public void setController(Controller<?> controller) {
        this.controller = controller;
    }

    public void setViewer(Viewer<?> viewer) {
        this.viewer = viewer;
    }
}