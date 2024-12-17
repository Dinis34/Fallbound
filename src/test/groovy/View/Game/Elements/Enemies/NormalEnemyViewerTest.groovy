package View.Game.Elements.Enemies

import Fallbound.GUI.GUI
import Fallbound.Model.Game.Elements.Enemies.NormalEnemy
import Fallbound.Model.Game.Scene
import Fallbound.Model.Vector
import Fallbound.View.Theme
import Fallbound.View.Game.Elements.Enemies.NormalEnemyViewer
import spock.lang.Specification

class NormalEnemyViewerTest extends Specification {

    def "normal enemy is drawn correctly"() {
        given:
        def gui = Mock(GUI)
        def enemy = new NormalEnemy(new Vector(0, 0), Mock(Scene) as Scene)
        def normalEnemyViewer = new NormalEnemyViewer()
        def offset = 0

        when:
        normalEnemyViewer.draw(gui, enemy, offset)

        then:
        1 * gui.drawText(enemy.getPosition().toPosition().applyOffset(offset), "\u2588", Theme.FALLBOUND_RED)
    }
}
