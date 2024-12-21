package Fallbound.Model.Game

import Fallbound.Model.Game.Elements.Coin
import Fallbound.Model.Game.Elements.Player
import Fallbound.Model.Vector
import spock.lang.Specification

class PlayerTest extends Specification {

    def "player moves left"() {
        given:
        def scene = Mock(Scene)
        def player = new Player(new Vector(0, 0), scene)

        when:
        player.moveLeft()

        then:
        player.getVelocity().getX() == (double) -0.5
    }

    def "player moves right"() {
        given:
        def scene = Mock(Scene)
        def player = new Player(new Vector(0, 0), scene)

        when:
        player.moveRight()

        then:
        player.getVelocity().getX() == (double) 0.5
    }

    def "player jumps"() {
        given:
        def scene = Mock(Scene)
        def player = new Player(new Vector(0, 0), scene)
        player.setOnGround(true)

        when:
        player.jump()

        then:
        player.getVelocity().getY() == (double) -0.4
        !player.getOnGround()
    }

    def "gravity pulls player down when not on the ground"() {
        given:
        def scene = Mock(Scene)
        def player = new Player(new Vector(0, 0), scene)
        player.setOnGround(false)

        when:
        player.gravity()

        then:
        player.getVelocity().getY() == (double) 0.02
    }

    def "player doesn't fall faster than max speed"() {
        given:
        def scene = Mock(Scene)
        def player = new Player(new Vector(0, 0), scene)
        player.setOnGround(false)
        player.getVelocity().setY(0.4)

        when:
        player.gravity()

        then:
        player.getVelocity().getY() == (double) 0.4
    }

    def "player stops moving horizontally"() {
        given:
        def scene = Mock(Scene)
        def player = new Player(new Vector(0, 0), scene)
        player.getVelocity().setX(0.5)

        when:
        player.stop()

        then:
        player.getVelocity().getX() == 0
    }

    def "player collects a coin when colliding with it"() {
        given:
        def scene = Mock(Scene)
        def player = new Player(new Vector(0, 0), scene)
        def coin = Mock(Coin)
        coin.getPosition() >> new Vector(0, 0)
        scene.getCoins() >> [coin]
        scene.isColliding(_ as Vector, _ as Vector) >> true

        when:
        player.checkCoinCollision()

        then:
        1 * scene.removeCoin(coin)
        player.getCollectedCoins() == 1
    }
}
