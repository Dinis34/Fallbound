package Fallbound.Model.Game.Collectibles

import Fallbound.Model.Game.Elements.Collectibles.*
import Fallbound.Model.Game.Elements.Player
import Fallbound.Model.Game.Scene
import Fallbound.Model.Vector
import spock.lang.Specification

class CollectibleTest extends Specification {

    def position = Mock(Vector)

    def "BulletCountCollectible"() {
        given:
        def scene = Mock(Scene)
        def bulletCountCollectible = new BulletCountCollectible(position, scene)
        def spyBulletCountCollectible = Spy(bulletCountCollectible)
        def player = Mock(Player)

        when:
        spyBulletCountCollectible.onCollect(player)

        then:
        1 * player.getMaxNumBullets() >> 5
        1 * player.setMaxNumBullets(6)

        when:
        def cost = bulletCountCollectible.getCost()

        then:
        cost == 3

        when:
        def icon = bulletCountCollectible.getIcon()

        then:
        icon == "|"

        when:
        def description = bulletCountCollectible.getDescription()

        then:
        description == "increases bullet count"
    }

    def "BulletSpeedCollectible"() {
        given:
        def scene = Mock(Scene)
        def bulletSpeedCollectible = new BulletSpeedCollectible(position, scene)
        def spyBulletSpeedCollectible = Spy(bulletSpeedCollectible)
        def player = Mock(Player)

        when:
        player.getShootCooldown() >> 100
        spyBulletSpeedCollectible.onCollect(player)

        then:
        1 * player.setShootCooldown(30)

        when:
        player.getShootCooldown() >> 70
        bulletSpeedCollectible.onCollect(player)

        then:
        0 * player.setShootCooldown(_)

        when:
        def cost = bulletSpeedCollectible.getCost()

        then:
        cost == 8

        when:
        def icon = bulletSpeedCollectible.getIcon()

        then:
        icon == "⇢"

        when:
        def description = bulletSpeedCollectible.getDescription()

        then:
        description == "increases bullet speed"
    }

    def "HealthCollectible"() {
        given:
        def scene = Mock(Scene)
        def healthCollectible = new HealthCollectible(position, scene)
        def spyHealthCollectible = Spy(healthCollectible)
        def player = Mock(Player)

        when:
        player.getHealth() >> 3
        player.getMaxHealth() >> 5
        spyHealthCollectible.onCollect(player)

        then:
        1 * player.setHealth(4)

        when:
        player.getHealth() >> 5
        player.getMaxHealth() >> 5
        healthCollectible.onCollect(player)

        then:
        0 * player.setHealth(_)

        when:
        def cost = healthCollectible.getCost()

        then:
        cost == 5

        when:
        def icon = healthCollectible.getIcon()

        then:
        icon == "♥"

        when:
        def description = healthCollectible.getDescription()

        then:
        description == "increases health"
    }

    def "JumpCollectible"() {
        given:
        def scene = Mock(Scene)
        def jumpCollectible = new JumpCollectible(position, scene)
        def spyJumpCollectible = Spy(jumpCollectible)
        def player = Mock(Player)

        when:
        player.getJumpForce() >> 1.2
        spyJumpCollectible.onCollect(player)

        then:
        1 * player.setJumpForce({ it == 1.12 || Math.abs(it - 1.12) < 0.0001 })

        when:
        def cost = jumpCollectible.getCost()

        then:
        cost == 10

        when:
        def icon = jumpCollectible.getIcon()

        then:
        icon == "⬆"

        when:
        def description = jumpCollectible.getDescription()

        then:
        description == "increases jump height"
    }

    def "MaxHealthCollectible"() {
        given:
        def scene = Mock(Scene)
        def maxHealthCollectible = new MaxHealthCollectible(position, scene)
        def spyMaxHealthCollectible = Spy(maxHealthCollectible)
        def player = Mock(Player)

        when:
        player.getMaxHealth() >> 5
        spyMaxHealthCollectible.onCollect(player)

        then:
        1 * player.setMaxHealth(6)

        when:
        def cost = maxHealthCollectible.getCost()

        then:
        cost == 8

        when:
        def icon = maxHealthCollectible.getIcon()

        then:
        icon == "♡"

        when:
        def description = maxHealthCollectible.getDescription()

        then:
        description == "increases max health"
    }

    def "SpeedCollectible"() {
        given:
        def scene = Mock(Scene)
        def speedCollectible = new SpeedCollectible(position, scene)
        def spySpeedCollectible = Spy(speedCollectible)
        def player = Mock(Player)

        when:
        player.getMoveSpeed() >> 1.0
        spySpeedCollectible.onCollect(player)

        then:
        1 * player.setMoveSpeed({ it == 1.04 || Math.abs(it - 1.04) < 0.0001 })

        when:
        def cost = speedCollectible.getCost()

        then:
        cost == 12

        when:
        def icon = speedCollectible.getIcon()

        then:
        icon == "⚡"

        when:
        def description = speedCollectible.getDescription()

        then:
        description == "increases move speed"
    }
}

