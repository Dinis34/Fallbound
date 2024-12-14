package View.Game.Elements

import Fallbound.GUI.GUI
import Fallbound.Model.Game.Elements.Bullet
import Fallbound.Model.Vector
import Fallbound.View.Game.Elements.BulletViewer
import Fallbound.View.Theme
import spock.lang.Specification

class BulletViewerTest extends Specification {

    def "bullet is drawn correctly"() {
        given:
        def gui = Mock(GUI)
        def bullet = new Bullet(new Vector(0, 0))
        def bulletViewer = new BulletViewer()
        def offset = 0

        when:
        bulletViewer.draw(gui, bullet, offset)

        then:
        1 * gui.drawElement(bullet.getPosition().toPosition(), '|', Theme.FALLBOUND_RED)
    }
}
