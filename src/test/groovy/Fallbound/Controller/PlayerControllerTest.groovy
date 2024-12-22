package Fallbound.Controller

import Fallbound.Controller.Game.Elements.PlayerController
import Fallbound.Controller.Sound.SoundController
import Fallbound.Game
import Fallbound.Model.Game.Elements.Player
import Fallbound.Model.Game.Scene
import Fallbound.Model.Vector
import Fallbound.State.GameState
import spock.lang.Specification
import spock.lang.Subject

import java.awt.event.KeyEvent

class PlayerControllerTest extends Specification {
    Game game = Mock()
    Player player = Mock()
    Scene scene = Mock()
    SoundController soundController = Mock()
    Vector velocity = Mock()
    Vector position = new Vector(10, 10)

    @Subject
    PlayerController controller

    def setup() {
        player.getScene() >> scene
        player.getVelocity() >> velocity
        player.getPosition() >> position
        player.getLastPosition() >> position

        scene.getWalls() >> []
        scene.getCoins() >> []
        scene.getEnemies() >> []

        controller = new PlayerController(player)

        GroovyMock(SoundController, global: true)
        SoundController.getInstance() >> soundController
    }

    def "should handle left movement"() {
        when:
        controller.step(game, [KeyEvent.VK_LEFT] as Set, 0L)

        then:
        1 * player.moveLeft()
        1 * player.update()
        0 * player.moveRight()
        0 * player.stop()
    }

    def "should handle right movement"() {
        when:
        controller.step(game, [KeyEvent.VK_RIGHT] as Set, 0L)

        then:
        1 * player.moveRight()
        1 * player.update()
        0 * player.moveLeft()
        0 * player.stop()
    }

    def "should stop when no movement keys are pressed"() {
        when:
        controller.step(game, [] as Set, 0L)

        then:
        1 * player.stop()
        1 * player.update()
        0 * player.moveLeft()
        0 * player.moveRight()
    }

    def "should jump when on ground and space pressed"() {
        given:
        player.isOnGround() >> true

        when:
        controller.step(game, [KeyEvent.VK_SPACE] as Set, 0L)

        then:
        1 * player.jump()
        0 * player.shoot()
        1 * player.update()
    }

    def "should shoot when in air and space pressed"() {
        given:
        player.isOnGround() >> false

        when:
        controller.step(game, [KeyEvent.VK_SPACE] as Set, 0L)

        then:
        0 * player.jump()
        1 * player.shoot()
        1 * player.update()
    }

    def "should trigger game over when health is zero"() {
        given:
        player.getHealth() >> 0

        when:
        controller.step(game, [] as Set, 0L)

        then:
        1 * game.setState(GameState.GAME_OVER)
    }

    def "should update enemies after processing collisions"() {
        when:
        controller.step(game, [] as Set, 0L)

        then:
        1 * scene.updateEnemies()
    }

    def "should set player not on ground when no ground collision"() {
        given:
        scene.getWalls() >> []

        when:
        controller.step(game, [] as Set, 0L)

        then:
        1 * player.setOnGround(false)
    }
}