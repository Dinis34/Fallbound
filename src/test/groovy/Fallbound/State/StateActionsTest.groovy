package Fallbound.State

import Fallbound.Controller.Menu.*
import Fallbound.View.Menu.*
import Fallbound.Controller.Game.SceneController
import Fallbound.View.Game.SceneViewer
import Fallbound.Model.Game.Scene

import spock.lang.Specification

class StateActionsTest extends Specification {

    def "StateActions Start Menu State" () {
        given:
        def state = State.getInstance()

        when:
        state.updateState(GameState.START_MENU)
        state.stateActions()

        then:
        state.controller.getClass() == StartMenuController.class
        state.viewer.getClass() == StartMenuViewer.class
    }

    def "StateActions Pause Menu State" () {
        given:
        def state = State.getInstance()
        def scene = Mock(Scene)
        state.scene = scene

        def controller = Mock(PauseMenuController)
        def viewer = Mock(PauseMenuViewer)

        state.controller = controller
        state.viewer = viewer

        when:
        state.updateState(GameState.PAUSE_MENU)
        state.stateActions()

        then:
        state.controller.getClass() == PauseMenuController.class
        state.viewer.getClass() == PauseMenuViewer.class
    }

    def "StateActions New Game State" () {
        given:
        def state = State.getInstance()

        when:
        state.updateState(GameState.NEW_GAME)
        state.stateActions()

        then:
        state.controller.getClass() == SceneController.class
        state.viewer.getClass() == SceneViewer.class
    }

    def "StateActions Resume Game State" () {
        given:
        def state = State.getInstance()
        def sceneController = Mock(SceneController)
        state.sceneController = sceneController

        when:
        state.updateState(GameState.RESUME_GAME)
        state.stateActions()

        then:
        state.controller == sceneController
        state.viewer.getClass() == SceneViewer.class
    }

    def "StateActions Game Over State"() {
        given:
        def state = State.getInstance()

        when:
        state.updateState(GameState.GAME_OVER)
        state.stateActions()

        then:
        state.controller.getClass() == GameOverMenuController.class
        state.viewer.getClass() == GameOverMenuViewer.class
    }

    def "StateActions Quit Game State"() {
        given:
        def state = State.getInstance()

        when:
        state.updateState(GameState.QUIT_GAME)
        state.stateActions()

        then:
        noExceptionThrown()
    }
}
