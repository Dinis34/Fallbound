package Fallbound.Model.Game.Collectibles

import Fallbound.Model.Game.Elements.Collectibles.*
import Fallbound.Model.Game.Scene
import Fallbound.Model.Vector
import spock.lang.Specification

class CollectibleFactoryTest extends Specification {

    def "getRandomCollectibles"() {
        given:
        def basePosition = Mock(Vector)
        def scene = Mock(Scene)

        when:
        def collectibles = CollectibleFactory.getRandomCollectibles(basePosition, scene)

        then:
        collectibles.size() == 3
        collectibles.every { it instanceof Collectible }

        and:
        collectibles.each {
            assert it instanceof HealthCollectible ||
                    it instanceof MaxHealthCollectible ||
                    it instanceof SpeedCollectible        ||
                    it instanceof BulletCountCollectible     ||
                    it instanceof BulletSpeedCollectible        ||
                    it instanceof JumpCollectible
        }

        and:
        def collectibles2 = CollectibleFactory.getRandomCollectibles(basePosition, scene)
        collectibles != collectibles2 || collectibles*.class != collectibles2*.class
    }
}
