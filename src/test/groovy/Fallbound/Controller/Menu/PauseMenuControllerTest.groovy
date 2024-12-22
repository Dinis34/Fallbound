import Fallbound.Game
import Fallbound.State.GameState
import spock.lang.Specification
import java.awt.event.KeyEvent
import Fallbound.Model.Menu.PauseMenu
import Fallbound.Controller.Menu.PauseMenuController

class PauseMenuControllerTest extends Specification {
    def "test if goes up"() {
        given:
        PauseMenu menu = Mock(PauseMenu)
        PauseMenuController controller = new PauseMenuController(menu)

        when:
        controller.step(Mock(Game), [KeyEvent.VK_UP] as Set, 0L)

        then:
        1 * menu.previousOption()
    }

    def "test if goes down"() {
        given:
        PauseMenu menu = Mock(PauseMenu)
        PauseMenuController controller = new PauseMenuController(menu)

        when:
        controller.step(Mock(Game), [KeyEvent.VK_DOWN] as Set, 0L)

        then:
        1 * menu.nextOption()
    }

    def "test when continue is selected"() {
        given:
        PauseMenu menu = Mock(PauseMenu)
        Game game = Mock(Game)
        PauseMenuController controller = new PauseMenuController(menu)

        and:
        menu.isSelectedContinue() >> true

        when:
        controller.step(game, [KeyEvent.VK_ENTER] as Set, 0L)

        then:
        1 * game.setState(GameState.RESUME_GAME)
    }

    def "test when restart is selected"() {
        given:
        PauseMenu menu = Mock(PauseMenu)
        Game game = Mock(Game)
        PauseMenuController controller = new PauseMenuController(menu)

        and:
        menu.isSelectedRestart() >> true

        when:
        controller.step(game, [KeyEvent.VK_ENTER] as Set, 0L)

        then:
        1 * game.setState(GameState.NEW_GAME)
    }

    def "test when exit to menu is selected"() {
        given:
        PauseMenu menu = Mock(PauseMenu)
        Game game = Mock(Game)
        PauseMenuController controller = new PauseMenuController(menu)

        and:
        menu.isSelectedExit() >> true

        when:
        controller.step(game, [KeyEvent.VK_ENTER] as Set, 0L)

        then:
        1 * game.setState(GameState.START_MENU)
    }

    def "test when exit to desktop is selected"() {
        given:
        PauseMenu menu = Mock(PauseMenu)
        Game game = Mock(Game)
        PauseMenuController controller = new PauseMenuController(menu)

        and:
        menu.isSelectedExitToDesktop() >> true

        when:
        controller.step(game, [KeyEvent.VK_ENTER] as Set, 0L)

        then:
        1 * game.setState(GameState.QUIT_GAME)
    }

    def "test doing nothing"() {
        given:
        PauseMenu menu = Mock(PauseMenu)
        PauseMenuController controller = new PauseMenuController(menu)

        when:
        controller.step(Mock(Game), [] as Set, 0L)

        then:
        0 * menu._
    }

    def "test if it can handle multiple keys"() {
        given:
        PauseMenu menu = Mock(PauseMenu)
        PauseMenuController controller = new PauseMenuController(menu)
        Game game = Mock(Game)

        and:
        menu.isSelectedContinue() >> false
        menu.isSelectedExit() >> true

        when:
        controller.step(game, [KeyEvent.VK_DOWN, KeyEvent.VK_ENTER] as Set, 0L)

        then:
        1 * menu.nextOption()
        1 * game.setState(GameState.START_MENU)
    }

    def "test if it can handle multiple keys v2"() {
        given:
        PauseMenu menu = Mock(PauseMenu)
        PauseMenuController controller = new PauseMenuController(menu)

        when:
        controller.step(Mock(Game), [KeyEvent.VK_UP] as Set, 0L)
        controller.step(Mock(Game), [KeyEvent.VK_DOWN] as Set, 0L)

        then:
        1 * menu.previousOption()
        1 * menu.nextOption()
    }
}
