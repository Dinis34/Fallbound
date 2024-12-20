package Fallbound.State

import Fallbound.Model.Game.Scene
import Fallbound.GUI.GUI
import Fallbound.Game
import Fallbound.Controller.Controller
import Fallbound.View.Viewer
import Fallbound.Controller.Menu.MenuController

import spock.lang.Specification

class StateTest extends Specification {

    def "Update State"(){
        given:
        def state = State.getInstance()

        when:
        state.UpdateState(GameState.NEW_GAME)

        then:
        state.getCurrentState() == GameState.NEW_GAME
    }

    def "Update State when New State is Pause Menu"(){
        given:
        def state = State.getInstance()
        def mockScene = Mock(Scene)

        def sceneField = State.class.getDeclaredField("scene")
        sceneField.setAccessible(true)
        sceneField.set(state, mockScene)

        when:
        state.UpdateState(GameState.PAUSE_MENU)

        then:
        state.getCurrentState() == GameState.PAUSE_MENU
        1 * mockScene.setPaused(true)

    }

    def "Update State when Current State is Pause Menu and New State is not Pause Menu"(){
        given:
        def state = State.getInstance()
        def mockScene = Mock(Scene)

        def sceneField = State.class.getDeclaredField("scene")
        sceneField.setAccessible(true)
        sceneField.set(state, mockScene)

        state.setCurrentState(GameState.PAUSE_MENU)

        when:
        state.UpdateState(GameState.NEW_GAME)

        then:
        state.getCurrentState() == GameState.NEW_GAME
        1 * mockScene.setPaused(false)
    }

    def "Update to Previous"(){
        given:
        def state = State.getInstance()
        def stateSpy = Spy(state)

        when:
        stateSpy.UpdateState(GameState.START_MENU)
        stateSpy.UpdateState(GameState.NEW_GAME)
        stateSpy.UpdateToPrevious()

        then:

        stateSpy.currentState == GameState.START_MENU
        stateSpy.previousState == GameState.NEW_GAME
        3 * stateSpy.StateActions()
    }

    def "State Step"() {
        given:
        def gui = Mock(GUI)
        def game = Mock(Game)
        def state = State.getInstance()
        def controller = Mock(Controller)
        def viewer = Mock(Viewer)
        def stateSpy = Spy(state)

        stateSpy.setController(controller)
        stateSpy.setViewer(viewer)

        when:
        stateSpy.step(gui, game, 0)

        then:
        1 * gui.getNextAction()
        1 * viewer.draw(_, _)
        1 * controller.step(_, _, _)
    }

    def "State Step with Controller Null"() {
        given:
        def gui = Mock(GUI)
        def game = Mock(Game)
        def state = State.getInstance()
        def viewer = Mock(Viewer)
        def controller = null
        def stateSpy = Spy(state)

        stateSpy.setController(controller)
        stateSpy.setViewer(viewer)

        gui.getNextAction() >> null

        when:
        stateSpy.step(gui, game, 0)

        then:
        1 * stateSpy.StateActions()
        1 * gui.getNextAction()
        1 * viewer.draw(_, _)
        0 * controller.step(_, _, _)
    }

    def "State Step when Controller is MenuController"() {
        given:
        def gui = Mock(GUI)
        def game = Mock(Game)
        def state = State.getInstance()
        def menuController = Mock(MenuController)
        def viewer = Mock(Viewer)
        def stateSpy = Spy(state)

        stateSpy.setController(menuController)
        stateSpy.setViewer(viewer)

        when:
        stateSpy.step(gui, game, 0)

        then:
        1 * gui.getNextAction()
        1 * gui.getNextSingleAction()
        1 * menuController.step(_, _, _)
        1 * viewer.draw(_, _)
    }

}
