package Fallbound.Controller.Game.Elements

import Fallbound.Model.Game.Elements.Enemies.Enemy
import Fallbound.Model.Game.Elements.Player
import Fallbound.Model.Game.Elements.Wall
import Fallbound.Model.Game.Scene
import spock.lang.Specification
import Fallbound.Model.Vector;

class FloatingEnemyControllerTest extends Specification{
    def "Enemy does not move before cooldown expires"() {
        given:
            def player = Mock(Player)
            def enemy = Mock(Enemy)
            def scene = Mock(Scene)
            def moveCooldown = 1000L
            def initialEnemyPosition = new Vector(5, 5)
            def playerPosition = new Vector(10, 5)
            player.getPosition() >> playerPosition
            enemy.getPosition() >> initialEnemyPosition
            scene.getPlayer() >> player
            scene.getWalls() >> []
            scene.isColliding(_, _) >> false
            def controller = new FloatingEnemyController(enemy, scene, moveCooldown)
            controller.move()
        when:
            controller.move()
        then:
            0 * enemy.setPosition(_)
    }

    def "Enemy moves after cooldown expires"(){
        given:
            def player = Mock(Player)
            def enemy = Mock(Enemy)
            def scene = Mock(Scene)
            def moveCooldown = 500L
            def currentTime = System.currentTimeMillis()
            def initialEnemyPosition = new Vector(5, 5)
            def playerPosition = new Vector(10, 5)

            player.getPosition() >> playerPosition
            enemy.getPosition() >> initialEnemyPosition
            scene.getPlayer() >> player
            scene.getWalls() >> []
            scene.isColliding(_, _) >> false
            def controller = new FloatingEnemyController(enemy, scene, moveCooldown)

        when:
            controller.move()
            Thread.sleep(moveCooldown)
        then:
            1 * enemy.setPosition(new Vector(6, 5))
    }

    def "enemy wont move into wall"() {
        given:
        def player = Mock(Player)
        def enemy = Mock(Enemy)
        def scene = Mock(Scene)
        def moveCooldown = 500L
        def initialEnemyPosition = new Vector(5, 5)
        def playerPosition = new Vector(10, 5)
        def wallPosition = new Vector(6, 5)
        player.getPosition() >> playerPosition
        enemy.getPosition() >> initialEnemyPosition
        scene.getPlayer() >> player
        scene.getWalls() >> [new Wall(wallPosition)]
        scene.isColliding(_, _) >> { Vector next, Vector wall ->
            next == wall
        }
        def controller = new FloatingEnemyController(enemy, scene, moveCooldown)

        when:
        controller.move()

        then:
        0 * enemy.setPosition(_)
    }

    def "enemy should damage player if they touch"() {
        given:
            def player = Mock(Player)
            def enemy = Mock(Enemy)
            def scene = Mock(Scene)
            def moveCooldown = 500L
            def initialEnemyPosition = new Vector(5, 5)
            def playerPosition = new Vector(6, 5)
            player.getPosition() >> playerPosition
            enemy.getPosition() >> initialEnemyPosition
            scene.getPlayer() >> player
            scene.getWalls() >> []
            scene.isColliding(_, _) >> { Vector next, Vector target ->
                next == target
            }
            def controller = new FloatingEnemyController(enemy, scene, moveCooldown)

        when:
            controller.move()

        then:
            1 * player.takeDamage()
            0 * enemy.setPosition(_)
    }
}

