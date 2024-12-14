package Fallbound.State

import Fallbound.Controller.Game.SceneController
import Fallbound.Controller.Menu.GameOverMenuController
import Fallbound.Controller.Menu.PauseMenuController
import Fallbound.Controller.Menu.StartMenuController
import Fallbound.GUI.GUI
import Fallbound.Game
import Fallbound.Model.Game.Scene
import Fallbound.View.Game.SceneViewer
import Fallbound.View.Menu.GameOverMenuViewer
import Fallbound.View.Menu.PauseMenuViewer
import Fallbound.View.Menu.StartMenuViewer
import spock.lang.Specification


class StateTest extends Specification {

    def "test if singleton pattern is being correctly implemented"() {
        when:
        def state1 = State.getInstance()
        def state2 = State.getInstance()

        then:
        state1 == state2
    }

    def "test UpdateState() method when newState is PAUSE_MENU, aka check if the scene is paused"() {
        setup:
        def state = State.getInstance()
        state.UpdateState(GameState.NEW_GAME)
        Scene scene = state.getScene()

        when:
        state.UpdateState(GameState.PAUSE_MENU)

        then:
        state.currentState == GameState.PAUSE_MENU
        state.previousState == GameState.NEW_GAME
        scene.isPaused == true
    }

    def "test UpdateState() from PAUSE_MENU to NEW_GAME, aka restarting"() {
        setup:
        def state = State.getInstance()
        state.UpdateState(GameState.NEW_GAME)
        state.UpdateState(GameState.PAUSE_MENU)
        Scene scene = state.getScene()

        when:
        state.UpdateState(GameState.NEW_GAME)

        then:
        state.currentState == GameState.NEW_GAME
        state.previousState == GameState.PAUSE_MENU
        scene != null
        scene.isPaused == false
    }

    def "test UpdateState() from PAUSE_MENU to RESUME_GAME, aka continuing after pause"(){
        setup:
        def state = State.getInstance()
        state.UpdateState(GameState.PAUSE_MENU)
        state.UpdateState(GameState.RESUME_GAME)
        Scene scene = state.getScene()

        expect:
        state.currentState == GameState.RESUME_GAME
        state.previousState == GameState.PAUSE_MENU
        scene != null
        scene.isPaused == false
    }

    def "test StateActions for START_MENU"() {
        setup:
        def state = State.getInstance()
        state.UpdateState(GameState.START_MENU)

        when:
        state.StateActions()

        then:
        state.controller instanceof StartMenuController
        state.viewer instanceof StartMenuViewer
    }

    def "test StateActions for PAUSE_MENU"() {
        setup:
        def state = State.getInstance()
        state.UpdateState(GameState.NEW_GAME)

        when:
        state.UpdateState(GameState.PAUSE_MENU)
        state.StateActions()

        then:
        state.controller instanceof PauseMenuController
        state.viewer instanceof PauseMenuViewer
    }

    def "test StateActions for NEW_GAME"() {
        setup:
        def state = State.getInstance()
        Scene scene = state.getScene()

        when:
        state.UpdateState(GameState.NEW_GAME)
        state.StateActions()

        then:
        state.controller instanceof SceneController
        state.viewer instanceof SceneViewer
        scene != null
    }

    def "test StateActions for RESUME_GAME"(){
        setup:
        def state = State.getInstance()
        Scene scene = state.getScene()

        when:
        state.UpdateState(GameState.RESUME_GAME)
        state.StateActions()

        then:
        state.controller instanceof SceneController
        state.viewer instanceof SceneViewer
        scene != null
    }

    def "test StateActions for GAME_OVER"(){
        setup:
        def state = State.getInstance()

        when:
        state.UpdateState(GameState.GAME_OVER)
        state.StateActions()

        then:
        state.controller instanceof GameOverMenuController
        state.viewer instanceof GameOverMenuViewer
    }

    def "test StateActions for QUIT_GAME"(){
        setup:
        def state = State.getInstance()

        when:
        state.UpdateState(GameState.QUIT_GAME)
        state.StateActions()

        then:
        0 * state._
    }


    def "test getCurrentState"(){
        setup:
        def state = State.getInstance()

        when:
        state.UpdateState(GameState.PAUSE_MENU)

        then:
        state.getCurrentState() == GameState.PAUSE_MENU
    }

    def "test getPreviousState"(){
        setup:
        def state = State.getInstance()

        when:
        state.UpdateState(GameState.PAUSE_MENU)
        state.UpdateState(GameState.RESUME_GAME)

        then:
        state.getPreviousState() == GameState.PAUSE_MENU
    }

    //todo finish test step method (idk how it's currently behaving)

}
