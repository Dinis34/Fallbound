package View.Menu

import Fallbound.GUI.GUI
import Fallbound.Model.Menu.GameOverMenu
import Fallbound.Model.Menu.PauseMenu
import Fallbound.Model.Menu.StartMenu
import Fallbound.Model.Position
import Fallbound.View.Menu.GameOverMenuViewer
import Fallbound.View.Menu.PauseMenuViewer
import Fallbound.View.Menu.StartMenuViewer
import spock.lang.Specification

class MenuViewerTests extends Specification {

    def "draw elements Start Menu"() {
        given:
        def gui = Mock(GUI.class)
        def menu = new StartMenu()
        def startMenuViewer = new StartMenuViewer(menu)

        when:
        startMenuViewer.drawElements(gui,0)

        then:
        16 * gui.drawText(_,_,_)
    }

    def "draw elements GameOver Menu"(){
        given:
        def gui = Mock(GUI.class)
        def menu = new GameOverMenu()
        def gameOverMenuViewer = new GameOverMenuViewer(menu)

        when:
        gameOverMenuViewer.drawElements(gui,0)

        then:
        4 * gui.drawText(_,_,_)

    }

    def "Draw elements Pause Menu"(){
        given:
        def gui = Mock(GUI.class)
        def menu = new PauseMenu()
        def pauseMenuViewer = new PauseMenuViewer(menu)

        when:
        pauseMenuViewer.drawElements(gui,0)

        then:
        5 * gui.drawText(_,_,_)

    }

    def "Draw Options"() {
        given:
        def MenuViewer = Spy(PauseMenuViewer.class)
        def gui = Mock(GUI)
        def pauseMenu = new PauseMenu()

        pauseMenu.nextOption()
        MenuViewer.getModel() >> pauseMenu

        when:
        MenuViewer.drawOptions(gui)

        then:
        1 * MenuViewer.drawOptions(gui)
        1 * gui.drawText(new Position(10, 5 + 1 * 1), "\u2592" + pauseMenu.getOption(1), _)
        1 * gui.drawText(new Position(10, 5 + 1 * 3), pauseMenu.getOption(3), _)
        2 * gui.drawText(_, _, _)
    }

    def "Draw Menu Title"(){
        given:
        def MenuViewer = Mock(MenuViewer)
        def gui = Mock(GUI)
        def position = Mock(Position)

        when:
        MenuViewer.drawMenuTitle(gui,"123","123",position)

        then:
        1 * MenuViewer.drawMenuTitle(_,_,_,_)
    }
}
