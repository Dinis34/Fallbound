package Fallbound.Controller.Menu

import Fallbound.Game
import Fallbound.Model.Menu.GameOverMenu
import Fallbound.Model.Menu.StartMenu
import Fallbound.State.GameState
import spock.lang.Specification

import java.awt.event.KeyEvent

class GameOverMenuControllerTest extends Specification{
    def "should update high score when current score is higher"() {
        given:
            def menu = Mock(GameOverMenu)
        when:
            GameOverMenuController controller = new GameOverMenuController(menu, 100000)
        then:
            1 * menu.setNewHighScore(true)
    }

    def "should not update high score when current score is lower"() {
        given:
            def menu = Mock(GameOverMenu)
            def controller = new GameOverMenuController(menu, 2)

        when:
            controller.checkAndUpdateHighScore()

        then:
            1 * menu.setNewHighScore(false)
    }

    def "test if goes up"() {
        given:
        GameOverMenu menu = Mock(GameOverMenu)
        int score = 11307
        GameOverMenuController controller = new GameOverMenuController(menu, score)

        when:
        controller.step(Mock(Game), [KeyEvent.VK_UP] as Set, 0L)

        then:
        1 * menu.previousOption()
    }
    def "test if goes down"() {
        given:
        GameOverMenu menu = Mock(GameOverMenu)
        int score = 11307
        GameOverMenuController controller = new GameOverMenuController(menu, score)

        when:
        controller.step(Mock(Game), [KeyEvent.VK_DOWN] as Set, 0L)

        then:
        1 * menu.nextOption()
    }

    def "test when restart is selected"() {
        given:
        GameOverMenu menu = Mock(GameOverMenu)
        Game game = Mock(Game)
        int score = 11307
        GameOverMenuController controller = new GameOverMenuController(menu, score)

        and:
        menu.isSelectedRestart() >> true

        when:
        controller.step(game, [KeyEvent.VK_ENTER] as Set, 0L)

        then:
        1 * game.setState(GameState.NEW_GAME)
    }

    def "test when exit to start menu is selected"() {
        given:
        GameOverMenu menu = Mock(GameOverMenu)
        Game game = Mock(Game)
        int score = 11307
        GameOverMenuController controller = new GameOverMenuController(menu, score)


        and:
        menu.isSelectedExit() >> true

        when:
        controller.step(game, [KeyEvent.VK_ENTER] as Set, 0L)

        then:
        1 * game.setState(GameState.START_MENU)
    }

    def "test when exit to desktop is selected"() {
        given:
        GameOverMenu menu = Mock(GameOverMenu)
        int score = 11307
        Game game = Mock(Game)
        GameOverMenuController controller = new GameOverMenuController(menu, score)

        and:
        menu.isSelectedExitToDesktop() >> true

        when:
        controller.step(game, [KeyEvent.VK_ENTER] as Set, 0L)

        then:
        1 * game.setState(GameState.QUIT_GAME)
    }

    def "test do nothing"() {
        given:
        GameOverMenu menu = Mock(GameOverMenu)
        int score = 11307
        GameOverMenuController controller = new GameOverMenuController(menu, score)

        when:
        controller.step(Mock(Game), [] as Set, 0L)

        then:
        0 * menu._
    }

    def "test if it handles more than one key"() {
        given:
        GameOverMenu menu = Mock(GameOverMenu)
        int score = 11307
        GameOverMenuController controller = new GameOverMenuController(menu, score)
        Game game = Mock(Game)

        and:
        menu.isSelectedRestart() >> false
        menu.isSelectedExit() >> true

        when:
        controller.step(game, [KeyEvent.VK_DOWN, KeyEvent.VK_ENTER] as Set, 0L)

        then:
        1 * menu.nextOption()
        1 * game.setState(GameState.START_MENU)
    }
}