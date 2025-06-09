package Fallbound

import Fallbound.GUI.GUI
import Fallbound.State.GameState
import Fallbound.State.State
import spock.lang.Specification

class GameTest extends Specification {

    def "getState should return the current state"() {
        given:
        def game = new Game()

        expect:
        game.getState() == State.getInstance()
    }

    def "setState should update the game state"() {
        given:
        def game = new Game()
        def newState = GameState.RESUME_GAME

        when:
        game.setState(newState)

        then:
        game.getState().getCurrentState() == newState
    }

    def "startGame should run the game loop until QUIT_GAME state"() {
        given:
        def state = Mock(State)
        def gui = Mock(GUI)
        state.getCurrentState() >> GameState.QUIT_GAME
        def game = new Game(state, gui)

        when:
        game.startGame()

        then:
        1 * gui.close()
    }

    def "startGame should sleep for the correct amount of time"() {
        given:
        def state = Mock(State)
        def gui = Mock(GUI)
        def game = Spy(Game, constructorArgs: [state, gui])
        state.getCurrentState() >>> [GameState.RESUME_GAME, GameState.QUIT_GAME]

        when:
        game.startGame()

        then:
        1 * game.sleep({ it >= 0 })
    }

    def "main should handle exceptions during game initialization"() {
        given:
        def game = Spy(Game)
        game.startGame() >> { throw new NullPointerException("Initialization failed") }

        when:
        Game.main([] as String[])

        then:
        thrown(NullPointerException)
    }
}