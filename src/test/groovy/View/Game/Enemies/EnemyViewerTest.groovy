package View.Game.Enemies

import Fallbound.GUI.GUI
import Fallbound.Model.Game.Elements.Enemies.NormalEnemy
import Fallbound.Model.Game.Elements.Enemies.ShellEnemy
import Fallbound.Model.Game.Elements.Enemies.SpikeEnemy
import Fallbound.Model.Game.Scene
import Fallbound.Model.Vector
import Fallbound.View.Game.Elements.Enemies.NormalEnemyViewer
import Fallbound.View.Game.Elements.Enemies.ShellEnemyViewer
import Fallbound.View.Game.Elements.Enemies.SpikeEnemyViewer
import Fallbound.View.Theme
import spock.lang.Specification

class EnemyViewerTest extends Specification {

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
