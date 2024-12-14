package View.Game.Elements

import Fallbound.GUI.GUI
import Fallbound.Model.Game.Elements.BreakableWall
import Fallbound.Model.Vector
import Fallbound.View.Game.Elements.BreakableWallViewer
import Fallbound.View.Theme
import spock.lang.Specification

class BreakableWallViewerTest extends Specification {

    def "breakable wall is drawn correctly"() {
        given:
        def gui = Mock(GUI)
        def breakableWall = new BreakableWall(new Vector(0, 0))
        def breakableWallViewer = new BreakableWallViewer()
        def offset = 2

        when:
        breakableWallViewer.draw(gui, breakableWall, offset)

        then:
        1 * gui.drawText(breakableWall.getPosition().toPosition().applyOffset(offset), "\u2591", Theme.FALLBOUND_WHITE)
    }
}
