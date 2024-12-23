package Fallbound.Controller.Game.Elements

import java.lang.reflect.Field
import spock.lang.Specification
import Fallbound.Controller.Game.SceneController
import Fallbound.Model.Game.Elements.Player
import Fallbound.Model.Game.Elements.Enemies.Enemy
import Fallbound.Model.Game.Scene
import Fallbound.Game
import Fallbound.State.GameState

import java.awt.event.KeyEvent

class SceneControllerTest extends Specification {

    def "should call playerController step method"() {
        given:
        def player = Mock(Player)
        def playerController = Mock(PlayerController)
        def scene = Mock(Scene)
        def game = Mock(Game)
        def keys = [KeyEvent.VK_LEFT] as Set
        def time = 1000L
        scene.getPlayer() >> player
        scene.getEnemies() >> []

        def controller = new SceneController(scene)

        Field playerControllerField = SceneController.class.getDeclaredField("playerController")
        playerControllerField.setAccessible(true)
        playerControllerField.set(controller, playerController)

        when:
        controller.step(game, keys, time)

        then:
        1 * playerController.step(game, keys, time)
    }

    def "should move enemies in the scene"() {
        given:
        def enemy = Mock(Enemy)
        def player = Mock(Player)
        def scene = Mock(Scene)
        def game = Mock(Game)
        def keys = [] as Set
        def time = 1000L
        scene.getPlayer() >> player
        scene.getEnemies() >> [enemy]

        def controller = new SceneController(scene)

        Field playerControllerField = SceneController.class.getDeclaredField("playerController")
        playerControllerField.setAccessible(true)
        playerControllerField.set(controller, Mock(PlayerController))

        when:
        controller.step(game, keys, time)

        then:
        1 * enemy.move()
    }

    def "should pause the game when escape key is pressed"() {
        given:
        def player = Mock(Player)
        def enemy = Mock(Enemy)
        def scene = Mock(Scene)
        def game = Mock(Game)
        def keys = [KeyEvent.VK_ESCAPE] as Set
        def time = 1000L
        scene.getPlayer() >> player
        scene.getEnemies() >> [enemy]

        def controller = new SceneController(scene)

        Field playerControllerField = SceneController.class.getDeclaredField("playerController")
        playerControllerField.setAccessible(true)
        playerControllerField.set(controller, Mock(PlayerController))

        when:
        controller.step(game, keys, time)

        then:
        1 * game.setState(GameState.PAUSE_MENU)
    }

    def "should not pause the game when escape key is not pressed"() {
        given:
        def player = Mock(Player)
        def enemy = Mock(Enemy)
        def scene = Mock(Scene)
        def game = Mock(Game)
        def keys = [] as Set
        def time = 1000L
        scene.getPlayer() >> player
        scene.getEnemies() >> [enemy]

        def controller = new SceneController(scene)

        Field playerControllerField = SceneController.class.getDeclaredField("playerController")
        playerControllerField.setAccessible(true)
        playerControllerField.set(controller, Mock(PlayerController))

        when:
        controller.step(game, keys, time)

        then:
        0 * game.setState(GameState.PAUSE_MENU)
    }
}