package Fallbound.View.Game.Elements

import Fallbound.GUI.GUI
import Fallbound.Model.Game.Elements.BreakableWall
import Fallbound.Model.Game.Elements.Bullet
import Fallbound.Model.Game.Elements.Coin
import Fallbound.Model.Game.Elements.Player
import Fallbound.Model.Game.Elements.Wall
import Fallbound.Model.Game.Scene
import Fallbound.Model.Vector
import Fallbound.View.Game.Elements.BreakableWallViewer
import Fallbound.View.Game.Elements.BulletViewer
import Fallbound.View.Game.Elements.CoinViewer
import Fallbound.View.Game.Elements.PlayerViewer
import Fallbound.View.Game.Elements.WallViewer
import Fallbound.View.Theme
import spock.lang.Specification

class ElementsViewerTest extends Specification {
    def "player is drawn correctly"() {
        given:
        def gui = Mock(GUI)
        def player = new Player(new Vector(0, 0), Mock(Scene) as Scene)
        def playerViewer = new PlayerViewer()
        def offset = 0;

        when:
        playerViewer.draw(gui, player, offset)

        then:
        1 * gui.drawText(player.getPosition().toPosition().applyOffset(offset), "\u2588", Theme.FALLBOUND_BLUE)
    }

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

    def "coin is drawn correctly"() {
        given:
        def gui = Mock(GUI)
        def coin = new Coin(new Vector(0, 0))
        def coinViewer = new CoinViewer()
        def offset = 0

        when:
        coinViewer.draw(gui, coin, offset)

        then:
        1 * gui.drawText(coin.getPosition().toPosition().applyOffset(offset), "O", Theme.FALLBOUND_GOLD)
    }

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

    def "breakable wall is drawn correctly"() {
        given:
        def gui = Mock(GUI)
        def breakableWall = new BreakableWall(new Vector(0, 0))
        def breakableWallViewer = new BreakableWallViewer()
        def offset = 0

        when:
        breakableWallViewer.draw(gui, breakableWall, offset)

        then:
        1 * gui.drawText(breakableWall.getPosition().toPosition().applyOffset(offset), "\u2591", Theme.FALLBOUND_WHITE)
    }
}
