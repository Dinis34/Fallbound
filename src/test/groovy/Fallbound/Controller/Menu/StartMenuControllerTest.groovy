package Fallbound.Controller.Menu

import Fallbound.Game
import Fallbound.State.GameState
import spock.lang.Specification
import java.awt.event.KeyEvent
import Fallbound.Model.Menu.StartMenu

class StartMenuControllerTest extends Specification {

    def "test if goes up"() {
        given:
        StartMenu menu = Mock(StartMenu)
        StartMenuController controller = new StartMenuController(menu)

        when:
        controller.step(Mock(Game), [KeyEvent.VK_UP] as Set, 0L)

        then:
        1 * menu.previousOption()
    }

    def "test if goes down"() {
        given:
        StartMenu menu = Mock(StartMenu)
        StartMenuController controller = new StartMenuController(menu)

        when:
        controller.step(Mock(Game), [KeyEvent.VK_DOWN] as Set, 0L)

        then:
        1 * menu.nextOption()
    }

    def "test when play is selected"() {
        given:
        StartMenu menu = Mock(StartMenu)
        Game game = Mock(Game)
        StartMenuController controller = new StartMenuController(menu)

        and:
        menu.isSelectedPlay() >> true

        when:
        controller.step(game, [KeyEvent.VK_ENTER] as Set, 0L)

        then:
        1 * game.setState(GameState.NEW_GAME)
    }

    def "test when exit is selected"() {
        given:
        StartMenu menu = Mock(StartMenu)
        Game game = Mock(Game)
        StartMenuController controller = new StartMenuController(menu)

        and:
        menu.isSelectedExit() >> true

        when:
        controller.step(game, [KeyEvent.VK_ENTER] as Set, 0L)

        then:
        1 * game.setState(GameState.QUIT_GAME)
    }

    def "test doing nothing"() {
        given:
        StartMenu menu = Mock(StartMenu)
        StartMenuController controller = new StartMenuController(menu)

        when: "no relevant key is pressed"
        controller.step(Mock(Game), [] as Set, 0L)

        then: "no methods on the menu should be called"
        0 * menu._
    }

    def "test if it can handle multiple keys"() {
        given:
        StartMenu menu = Mock(StartMenu)
        StartMenuController controller = new StartMenuController(menu)
        Game game = Mock(Game)

        and:
        menu.isSelectedPlay() >> false
        menu.isSelectedExit() >> true

        when:
        controller.step(game, [KeyEvent.VK_DOWN, KeyEvent.VK_ENTER] as Set, 0L)

        then:
        1 * menu.nextOption()
        1 * game.setState(GameState.QUIT_GAME)
    }
}
