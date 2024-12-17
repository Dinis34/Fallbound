package View.Game.Elements.Enemies

import Fallbound.GUI.GUI
import Fallbound.Model.Game.Elements.Enemies.SpikeEnemy
import Fallbound.Model.Game.Scene
import Fallbound.View.Theme
import Fallbound.Model.Vector
import Fallbound.View.Game.Elements.Enemies.SpikeEnemyViewer
import spock.lang.Specification

class SpikeEnemyViewerTest extends Specification {

    def "spike enemy is drawn correctly"() {
        given:
        def gui = Mock(GUI)
        def spikeEnemy = new SpikeEnemy(new Vector(0, 0), Mock(Scene) as Scene)
        def spikeEnemyViewer = new SpikeEnemyViewer()
        def offset = 0

        when:
        spikeEnemyViewer.draw(gui, spikeEnemy, offset)

        then:
        1 * gui.drawText(spikeEnemy.getPosition().toPosition().applyOffset(offset), "\u0394", Theme.FALLBOUND_RED)
    }
}