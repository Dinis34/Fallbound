package Fallbound.Controller.Game.Elements

import Fallbound.Model.Game.Elements.Enemies.Enemy
import Fallbound.Model.Game.Elements.Enemies.ShellEnemy
import Fallbound.Model.Game.Elements.Player
import Fallbound.Model.Game.Elements.Wall
import Fallbound.Model.Game.Scene
import Fallbound.Model.Vector
import spock.lang.Specification

class GroundEnemyControllerTest extends Specification {
    def "should move enemy in the current direction if the ground exists below the next position"() {
        given:
        def player = Mock(Player)
        def enemy = Mock(ShellEnemy)
        def scene = Mock(Scene)
        def moveCoolDown = 0L
        def currentTime = System.currentTimeMillis()
        def initialEnemyPosition = new Vector(5, 5)
        def playerPosition = new Vector(10, 5)
        def wallPosition1 = new Vector(5, 6)
        def wallPosition2 = new Vector(6, 6)
        def wallPosition3 = new Vector(7, 6)
        player.getPosition() >> playerPosition
        enemy.getPosition() >> initialEnemyPosition
        scene.getPlayer() >> player
        scene.getWalls() >> [new Wall(wallPosition1), new Wall(wallPosition2), new Wall(wallPosition3)]
        def controller = new GroundedEnemyController(enemy, scene)
        controller.direction >> 1

        when:
        controller.move()
        Thread.sleep(moveCoolDown)

        then:
        1 * scene.isColliding(new Vector(6, 6), new Vector(6, 6)) >> true
        1 * enemy.setPosition(new Vector(6, 5))
    }

    def "should't move enemy in the current direction if the ground doesn't exists below the next position and switch directions"() {
        given:
        def player = Mock(Player)
        def enemy = Mock(ShellEnemy)
        def scene = Mock(Scene)
        def moveCoolDown = 0L
        def currentTime = System.currentTimeMillis()
        def initialEnemyPosition = new Vector(5, 5)
        def playerPosition = new Vector(10, 5)
        def wallPosition1 = new Vector(5, 6)
        def wallPosition3 = new Vector(7, 6)
        player.getPosition() >> playerPosition
        enemy.getPosition() >> initialEnemyPosition
        scene.getPlayer() >> player
        scene.getWalls() >> [new Wall(wallPosition1), new Wall(wallPosition3)]
        def controller = new GroundedEnemyController(enemy, scene)
        controller.direction >> 1

        when:
        controller.move()
        Thread.sleep(moveCoolDown)

        then:
        0 * enemy.setPosition(new Vector(6, 5))
        controller.direction == -1
    }

    def "should damage the player when touching"() {
        given:
        def player = Mock(Player)
        def enemy = Mock(ShellEnemy)
        def scene = Mock(Scene)
        def moveCoolDown = 0L
        def currentTime = System.currentTimeMillis()
        def initialEnemyPosition = new Vector(5, 5)
        def playerPosition = new Vector(6, 5)
        def wallPosition1 = new Vector(5, 6)
        def wallPosition2 = new Vector(6, 6)
        def wallPosition3 = new Vector(7, 6)
        player.getPosition() >> playerPosition
        enemy.getPosition() >> initialEnemyPosition
        scene.getPlayer() >> player
        scene.getWalls() >> [new Wall(wallPosition1), new Wall(wallPosition2), new Wall(wallPosition3)]
        def controller = new GroundedEnemyController(enemy, scene)
        controller.direction >> 1
        when:
        controller.move()
        Thread.sleep(moveCoolDown)
        then:
        1 * scene.isColliding(new Vector(6, 6), new Vector(6, 6)) >> true
        1 * scene.isColliding(new Vector(6, 5), new Vector(6, 5)) >> true
        1 * scene.getPlayer().takeDamage()
    }

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
        def controller = new GroundedEnemyController(enemy, scene)
        controller.move()
        when:
        controller.move()
        then:
        0 * enemy.setPosition(_)
    }

}
