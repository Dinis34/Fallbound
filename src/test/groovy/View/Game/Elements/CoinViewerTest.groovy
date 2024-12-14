package View.Game.Elements

import Fallbound.GUI.GUI
import Fallbound.Model.Game.Elements.Coin
import Fallbound.Model.Vector
import Fallbound.View.Game.Elements.CoinViewer
import Fallbound.View.Theme
import spock.lang.Specification

class CoinViewerTest extends Specification {

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
}
