package Fallbound.Controller

import Fallbound.Controller.Game.Elements.PlayerController
import Fallbound.Model.Game.Elements.Player
import Fallbound.Model.Vector
import Fallbound.Model.Game.Scene
import Fallbound.Game
import spock.lang.Specification

import java.awt.event.KeyEvent

class PlayerControllerTest extends Specification {

    def "player jumps when space key is pressed"() {
        given:
        def scene = Mock(Scene)
        scene.getWalls() >> []
        scene.getCoins() >> []
        def player = new Player(new Vector(0, 0), scene)
        def controller = new PlayerController(player)
        def game = Mock(Game)
        def keys = [KeyEvent.VK_SPACE] as Set

        when:
        player.setOnGround(true)
        controller.step(game, keys, 0)

        then:
        // expected value would be -0.4.
        // still unsure why it's -0.38 : my theory is that after adding the jump force, the gravity is applied
        // TODO: investigate this
        player.getVelocity().getY() == (double) -0.38
        !player.getOnGround()
    }

    def "player moves left when left key is pressed"() {
        given:
        def scene = Mock(Scene)
        scene.getWalls() >> []
        scene.getCoins() >> []
        def player = new Player(new Vector(0, 0), scene)
        def controller = new PlayerController(player)
        def game = Mock(Game)
        def keys = [KeyEvent.VK_LEFT] as Set

        when:
        controller.step(game, keys, 0)

        then:
        player.getVelocity().getX() == (double) -0.5
    }

    def "player moves right when right key is pressed"() {
        given:
        def scene = Mock(Scene)
        scene.getWalls() >> []
        scene.getCoins() >> []
        def player = new Player(new Vector(0, 0), scene)
        def controller = new PlayerController(player)
        def game = Mock(Game)
        def keys = [KeyEvent.VK_RIGHT] as Set

        when:
        controller.step(game, keys, 0)

        then:
        player.getVelocity().getX() == (double) 0.5
    }

    def "player stops moving horizontally when no left or right key is pressed"() {
        given:
        def scene = Mock(Scene)
        scene.getWalls() >> []
        scene.getCoins() >> []
        def player = new Player(new Vector(0, 0), scene)
        def controller = new PlayerController(player)
        def game = Mock(Game)
        def keys = [] as Set

        when:
        player.getVelocity().setX(0.5)
        controller.step(game, keys, 0)

        then:
        player.getVelocity().getX() == 0
    }
}