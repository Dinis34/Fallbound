package Fallbound.Model.Game

import Fallbound.Model.Game.Elements.Coin
import Fallbound.Model.Game.Elements.Collectibles.Collectible
import Fallbound.Model.Game.Elements.Enemies.*
import Fallbound.Model.Game.Elements.Wall
import Fallbound.Model.Game.Elements.Bullet
import Fallbound.Model.Vector

import spock.lang.Specification

class SceneTest extends Specification {

    def "width"(){
        given:
        def scene = new Scene(10,10)

        expect:
        scene.getWidth() == 10
    }

    def "height"(){
        given:
        def scene = new Scene(10,10)

        expect:
        scene.getHeight() == 10
    }

    def "addCollectible()"() {
        given:
        def scene = new Scene(10, 10)
        def collectible = Mock(Collectible)

        when:
        scene.addCollectible(collectible)

        then:
        scene.getCollectibles().contains(collectible)
    }

    def "addNormalEnemy()"() {
        given:
        def scene = new Scene(10, 10)
        def x = 5
        def y = 5

        when:
        scene.addNormalEnemy(x, y)

        then:
        scene.getEnemies().size() == 1
        scene.getEnemies().get(0) instanceof NormalEnemy
        scene.getEnemies().get(0).getPosition() == new Vector(x, y)
    }

    def "addShellEnemy()"() {
        given:
        def scene = new Scene(10, 10)
        def x = 6
        def y = 4

        when:
        scene.addShellEnemy(x, y)

        then:
        scene.getEnemies().size() == 1
        scene.getEnemies().get(0) instanceof ShellEnemy
        scene.getEnemies().get(0).getPosition() == new Vector(x, y)
    }

    def "addSpikeEnemy()"() {
        given:
        def scene = new Scene(10, 10)
        def x = 3
        def y = 7

        when:
        scene.addSpikeEnemy(x, y)

        then:
        scene.getEnemies().size() == 1
        scene.getEnemies().get(0) instanceof SpikeEnemy
        scene.getEnemies().get(0).getPosition() == new Vector(x, y)
    }

    def "timeToString converts time correctly"() {
        given:
        def scene = new Scene(10, 10)

        expect:
        scene.timeToString(input) == expected

        where:
        input     || expected
        0         || "00:00.00"
        1000      || "00:01.00"
        60000     || "01:00.00"
        61000     || "01:01.00"
        123456    || "02:03.45"
        3599999   || "59:59.99"
    }

    def "removeEnemy removes the enemy and adds a coin at the same position"() {
        given:
        def scene = new Scene(100, 100)
        def enemyPosition = new Vector(10, 20)
        def enemy = new NormalEnemy(enemyPosition, scene)

        scene.getEnemies().add(enemy)

        expect:
        scene.getEnemies().contains(enemy)

        when:
        scene.removeEnemy(enemy)

        then:
        !scene.getEnemies().contains(enemy)
        scene.getCoins().any { coin -> coin.getPosition() == enemyPosition }
    }

    def "removeCoin()"() {
        given:
        def scene = new Scene(100, 100)
        def coinPosition = new Vector(15, 25)
        def coin = new Coin(coinPosition)

        scene.getCoins().add(coin)

        expect:
        scene.getCoins().contains(coin)

        when:
        scene.removeCoin(coin)

        then:
        !scene.getCoins().contains(coin)
    }

    def "getWalls()"() {
        given:
        def scene = new Scene(100, 100)
        def wall1 = new Wall(new Vector(10, 20))
        def wall2 = new Wall(new Vector(15, 25))

        when:
        scene.getWalls().add(wall1)
        scene.getWalls().add(wall2)

        then:
        scene.getWalls().contains(wall1)
        scene.getWalls().contains(wall2)
    }

    def "setPaused()"() {
        given:
        def scene = new Scene(100, 100)

        expect:
        !scene.isPaused

        when:
        scene.setPaused(true)

        then:
        scene.isPaused

        when:
        scene.setPaused(false)

        then:
        !scene.isPaused
    }

    def "getCameraOffset()"() {
        given:
        def scene = new Scene(100, 100)
        def player = scene.getPlayer()
        player.setPosition(new Vector(50, 60))

        when:
        scene.updateCameraOffset()

        then:
        scene.getCameraOffset() != 0
    }

    def "getBullets()"() {
        given:
        def scene = new Scene(100, 100)
        def bullet = Mock(Bullet)

        when:
        scene.addBullet(bullet)

        then:
        scene.getBullets().contains(bullet)
    }

    def "addBullet()"() {
        given:
        def scene = new Scene(100, 100)
        def bullet = Mock(Bullet)

        when:
        scene.addBullet(bullet)

        then:
        scene.getBullets().size() == 1
        scene.getBullets().get(0) == bullet
    }

    def "calculateNumberOfEnemies()"() {
        given:
        def scene = new Scene(100, 100)

        when:
        def calculateNumberOfEnemiesMethod = Scene.class.getDeclaredMethod('calculateNumberOfEnemies', long, int)
        calculateNumberOfEnemiesMethod.setAccessible(true)

        long elapsedTime = 60000
        int interval = 60000
        def result = calculateNumberOfEnemiesMethod.invoke(scene, elapsedTime, interval)

        then:
        result == 1

        when:
        elapsedTime = 120000
        result = calculateNumberOfEnemiesMethod.invoke(scene, elapsedTime, interval)

        then:
        result == 2

        when:
        elapsedTime = 90000
        interval = 90000
        result = calculateNumberOfEnemiesMethod.invoke(scene, elapsedTime, interval)

        then:
        result == 1
    }

    def "isColliding checks if two positions are colliding"() {
        given:
        def scene = new Scene(100, 100)
        def position1 = new Vector(10, 20)
        def position2 = new Vector(10, 20)
        def position3 = new Vector(15, 25)

        expect:
        scene.isColliding(position1, position2) == true
        scene.isColliding(position1, position3) == false
    }

    def "updateEnemies()"() {
        given:
        def scene = new Scene(100, 100)
        def enemy1 = new NormalEnemy(new Vector(10, 20), scene)
        def enemy2 = new NormalEnemy(new Vector(15, 25), scene)
        scene.getEnemies().add(enemy1)
        scene.getEnemies().add(enemy2)

        when:
        scene.updateEnemies()

        then:
        scene.getEnemies().any { enemy -> enemy.getPosition() != new Vector(10, 20) }
        scene.getEnemies().any { enemy -> enemy.getPosition() != new Vector(15, 25) }
    }

    def "buildShopPlatform adds shop collectibles"() {
        given:
        def scene = new Scene(100, 100)
        def yPosition = 50

        when:
        scene.buildShopPlatform(yPosition)

        then:
        scene.getCollectibles().size() > 0
    }

    def "handleBulletCollisions removes bullet on enemy collision"() {
        given:
        def scene = new Scene(100, 100)
        def enemy = new NormalEnemy(new Vector(10, 20), scene)
        def bullet = new Bullet(new Vector(10, 20))
        scene.getEnemies().add(enemy)
        scene.addBullet(bullet)

        when:
        scene.handleBullets()

        then:
        !scene.getBullets().contains(bullet)
    }
}
