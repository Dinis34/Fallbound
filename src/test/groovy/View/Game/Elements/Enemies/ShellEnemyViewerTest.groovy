package View.Game.Elements.Enemies

import Fallbound.GUI.GUI
import Fallbound.Model.Game.Elements.Enemies.ShellEnemy
import Fallbound.Model.Game.Scene
import Fallbound.Model.Vector
import Fallbound.View.Theme
import Fallbound.View.Game.Elements.Enemies.ShellEnemyViewer
import spock.lang.Specification

class ShellEnemyViewerTest extends Specification {

    def "shell enemy is drawn correctly"() {
        given:
        def gui = Mock(GUI)
        def shellEnemy = new ShellEnemy(new Vector(0, 0), Mock(Scene) as Scene)
        def shellEnemyViewer = new ShellEnemyViewer()
        def offset = 0

        when:
        shellEnemyViewer.draw(gui, shellEnemy, offset)

        then:
        1 * gui.drawText(shellEnemy.getPosition().toPosition().applyOffset(offset), "\u2229", Theme.FALLBOUND_RED)
    }
}
