package Fallbound.View.Menu

import Fallbound.GUI.GUI
import Fallbound.Model.Menu.GameOverMenu
import Fallbound.Model.Menu.PauseMenu
import Fallbound.Model.Menu.StartMenu
import Fallbound.Model.Position
import Fallbound.View.Theme
import spock.lang.Specification

class MenuViewerTests extends Specification {

    def "draw elements Start Menu"() {
        given:
        def gui = Mock(GUI.class)
        def menu = new StartMenu()
        def startMenuViewer = new StartMenuViewer(menu)

        when:
        startMenuViewer.drawElements(gui, 0)

        then:
        16 * gui.drawText(_, _, _)
    }

    def "draw elements GameOver Menu"() {
        given:
        def gui = Mock(GUI.class)
        def menu = new GameOverMenu()
        def gameOverMenuViewer = new GameOverMenuViewer(menu)

        when:
        gameOverMenuViewer.drawElements(gui, 0)

        then:
        4 * gui.drawText(_, _, _)

    }

    def "Draw elements Pause Menu"() {
        given:
        def gui = Mock(GUI.class)
        def menu = new PauseMenu()
        def pauseMenuViewer = new PauseMenuViewer(menu)

        when:
        pauseMenuViewer.drawElements(gui, 0)

        then:
        5 * gui.drawText(_, _, _)

    }

    def "Draw Options correctly renders all menu items with second option selected"() {
        given:
        def gui = Mock(GUI)
        def pauseMenu = new PauseMenu()
        def menuViewer = Spy(PauseMenuViewer, constructorArgs: [pauseMenu]) {
            getModel() >> pauseMenu
        }

        pauseMenu.nextOption()

        when:
        menuViewer.drawOptions(gui)

        then:
        interaction {
            1 * gui.drawText(
                    new Position(4, 25),
                    "continue",
                    Theme.FALLBOUND_LIGHT_GRAY
            )

            1 * gui.drawText(
                    new Position(4, 26),
                    "▒ restart",
                    Theme.FALLBOUND_WHITE
            )

            1 * gui.drawText(
                    new Position(4, 27),
                    "exit to main menu",
                    Theme.FALLBOUND_LIGHT_GRAY
            )

            1 * gui.drawText(
                    new Position(4, 28),
                    "exit to desktop",
                    Theme.FALLBOUND_LIGHT_GRAY
            )

            0 * gui.drawText(_, _, _)
        }

        and: "verify correct option is selected"
        pauseMenu.isSelectedRestart()
        !pauseMenu.isSelectedContinue()
        !pauseMenu.isSelectedExit()
        !pauseMenu.isSelectedExitToDesktop()
    }

    def "Draw Options correctly renders with last option selected"() {
        given:
        def gui = Mock(GUI)
        def pauseMenu = new PauseMenu()
        def menuViewer = Spy(PauseMenuViewer, constructorArgs: [pauseMenu]) {
            getModel() >> pauseMenu
        }

        pauseMenu.nextOption()
        pauseMenu.nextOption()
        pauseMenu.nextOption()

        when:
        menuViewer.drawOptions(gui)

        then:
        interaction {
            1 * gui.drawText(
                    new Position(4, 25),
                    "continue",
                    Theme.FALLBOUND_LIGHT_GRAY
            )

            1 * gui.drawText(
                    new Position(4, 26),
                    "restart",
                    Theme.FALLBOUND_LIGHT_GRAY
            )

            1 * gui.drawText(
                    new Position(4, 27),
                    "exit to main menu",
                    Theme.FALLBOUND_LIGHT_GRAY
            )

            1 * gui.drawText(
                    new Position(4, 28),
                    "▒ exit to desktop",
                    Theme.FALLBOUND_WHITE
            )

            0 * gui.drawText(_, _, _)
        }

        and: "verify correct option is selected"
        pauseMenu.isSelectedExitToDesktop()
        !pauseMenu.isSelectedContinue()
        !pauseMenu.isSelectedRestart()
        !pauseMenu.isSelectedExit()
    }

}
