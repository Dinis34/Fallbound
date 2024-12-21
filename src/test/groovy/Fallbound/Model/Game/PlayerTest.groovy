package Fallbound.Model.Game

import org.mockito.MockedStatic
import org.mockito.Mockito
import spock.lang.Specification
import spock.lang.Subject
import Fallbound.Model.Game.Elements.Player
import Fallbound.Model.Game.Elements.Bullet
import Fallbound.Model.Vector
import Fallbound.Controller.Sound.SoundController
import Fallbound.Model.Sound.SoundOption
import Fallbound.Model.Game.Elements.Wall

class PlayerTest extends Specification {
    @Subject
    Player player

    def scene = Mock(Scene)
    def soundController = Mockito.mock(SoundController)
    def mockPosition = new Vector(5, 5)

    def setup() {
        player = new Player(mockPosition, scene)
    }

    def "should initialize player with default values"() {
        expect:
        player.health == 5
        player.maxHealth == 5
        player.numBullets == 5
        player.maxNumBullets == 5
        player.shootCooldown == 350
        player.moveSpeed == (double) 0.45
        player.jumpForce == (double) -0.4
        !player.onGround
        player.collectedCoins == 0
    }

    def "should handle movement left"() {
        when:
        player.moveLeft()

        then:
        player.getVelocity().getX() == (double) -0.45
    }

    def "should handle movement right"() {
        when:
        player.moveRight()

        then:
        player.getVelocity().getX() == (double) 0.45
    }

    def "should stop movement"() {
        given:
        player.moveRight()

        when:
        player.stop()

        then:
        player.getVelocity().getX() == 0
    }

    def "should handle jump"() {
        try (MockedStatic<SoundController> utilities = Mockito.mockStatic(SoundController.class)) {
            utilities.when(SoundController::getInstance).thenReturn(soundController)

            when:
            player.jump()

            then:
            Mockito.verify(soundController, Mockito.times(1)).playSound(SoundOption.JUMP)
            player.getVelocity().getY() == (double) -0.4
            !player.onGround
        }
    }

    def "should apply gravity when in air"() {
        given:
        player.setOnGround(false)

        when:
        player.gravity()

        then:
        player.getVelocity().getY() <= 0.4 // MAX_FALL_SPEED
    }

    def "should handle shooting with available bullets"() {
        given:
        scene.getCameraOffset() >> 0
        player.setNumBullets(5)

        when:
        player.shoot()

        then:
        1 * scene.addBullet(_ as Bullet)
        player.getNumBullets() == 4
        player.getVelocity().getY() == (double) -0.175
    }

    def "should not shoot when out of bullets"() {
        given:
        player.setNumBullets(0)

        when:
        player.shoot()

        then:
        Mockito.verify(soundController, Mockito.times(0)).playSound(SoundOption.BULLET)
        0 * scene.addBullet(_)
    }

    def "should handle damage with cooldown"() {
        given:
        def oldHealth = player.getHealth()

        when:
        player.takeDamage()

        then:
        player.getHealth() == oldHealth - 1
    }

    def "should respect damage cooldown"() {
        given:
        def initialHealth = player.getHealth()

        when:
        player.takeDamage()
        sleep(100) // small delay
        player.takeDamage()

        then:
        player.getHealth() == initialHealth - 1
    }

    def "should handle movement collisions"() {
        given:
        def wall = Mock(Wall)
        scene.getWidth() >> 100
        scene.getWalls() >> [wall]
        scene.isColliding(_, _) >> true

        when:
        player.moveRight()
        player.move()

        then:
        player.getVelocity().getX() == 0
    }

    def "should handle scene boundaries"() {
        given:
        scene.getWidth() >> 10
        scene.getWalls() >> []

        when:
        player.moveRight()
        player.setPosition(new Vector(9.9, 5))
        player.move()

        then:
        player.getVelocity().getX() == 0
    }

    def "should throw exception on invalid scene state"() {
        given:
        scene.getWidth() >> { throw new IllegalStateException("Invalid scene state") }

        when:
        player.move()

        then:
        thrown(IllegalStateException)
    }

    def "should update player state"() {
        given:
        scene.getWidth() >> 100
        scene.getWalls() >> []
        scene.isColliding(_, _) >> false

        when:
        player.update()

        then:
        noExceptionThrown()
    }


}