package Fallbound.Model.Game.Elements.Enemies

import Fallbound.Controller.Game.Elements.EnemyController
import Fallbound.Model.Game.Scene
import Fallbound.Model.Vector
import spock.lang.Specification

class EnemyTest extends Specification {

    def "Enemy should set and get controller correctly"() {
        given:
        def position = new Vector(1, 2)
        def scene = Mock(Scene)
        def controller = Mock(EnemyController)

        and:
        def enemy = new Enemy(position, scene) {}

        when:
        enemy.setController(controller)

        then:
        enemy.getController() == controller
    }

    def "getScene"() {
        given:
        def position = new Vector(1, 2)
        def scene = Mock(Scene)

        and:
        def enemy = new Enemy(position, scene) {
        }

        expect:
        enemy.getPosition() == position
        enemy.getScene() == scene
    }

    def "Enemy should move if controller is set"() {
        given:
        def position = new Vector(1, 2)
        def scene = Mock(Scene)
        def controller = Mock(EnemyController)

        and:
        def enemy = new Enemy(position, scene) {
        }

        enemy.setController(controller)

        when:
        enemy.move()

        then:
        1 * controller.move()
    }

    def "Enemy should not move if controller is not set"() {
        given:
        def position = new Vector(1, 2)
        def scene = Mock(Scene)

        and:
        def enemy = new Enemy(position, scene) {
        }

        when:
        enemy.move()

        then:
        0 * _
    }
}
