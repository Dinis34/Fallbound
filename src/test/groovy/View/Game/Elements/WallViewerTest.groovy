package View.Game.Elements

import Fallbound.GUI.GUI
import Fallbound.Model.Game.Elements.Wall
import Fallbound.Model.Vector
import Fallbound.View.Game.Elements.WallViewer
import Fallbound.View.Theme
import spock.lang.Specification

class WallViewerTest extends Specification {

    def "wall is drawn correctly"() {
        given:
        def gui = Mock(GUI)
        def wall = new Wall(new Vector(0, 0))
        def wallViewer = new WallViewer()
        def offset = 0

        when:
        wallViewer.draw(gui, wall, offset)

        then:
        1 * gui.drawText(wall.getPosition().toPosition().applyOffset(offset), "\u2593", Theme.FALLBOUND_WHITE)
    }
}