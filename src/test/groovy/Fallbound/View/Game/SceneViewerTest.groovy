package Fallbound.View.Game

import Fallbound.Controller.Menu.GameOverMenuController
import Fallbound.GUI.GUI
import Fallbound.Model.Game.Elements.BreakableWall
import Fallbound.Model.Game.Elements.Bullet
import Fallbound.Model.Game.Elements.Coin
import Fallbound.Model.Game.Elements.Collectibles.Collectible
import Fallbound.Model.Game.Elements.Collectibles.MaxHealthCollectible
import Fallbound.Model.Game.Elements.Enemies.NormalEnemy
import Fallbound.Model.Game.Elements.Enemies.ShellEnemy
import Fallbound.Model.Game.Elements.Enemies.SpikeEnemy
import Fallbound.Model.Game.Elements.Player
import Fallbound.Model.Game.Elements.Wall
import Fallbound.Model.Game.Scene
import Fallbound.Model.Menu.GameOverMenu
import Fallbound.Model.Position
import Fallbound.Model.Vector
import Fallbound.View.Game.Elements.BreakableWallViewer
import Fallbound.View.Game.Elements.BulletViewer
import Fallbound.View.Game.Elements.CoinViewer
import Fallbound.View.Game.Elements.CollectibleViewer
import Fallbound.View.Game.Elements.Enemies.NormalEnemyViewer
import Fallbound.View.Game.Elements.Enemies.ShellEnemyViewer
import Fallbound.View.Game.Elements.Enemies.SpikeEnemyViewer
import Fallbound.View.Game.Elements.PlayerViewer
import Fallbound.View.Game.Elements.WallViewer
import spock.lang.Specification

class SceneViewerTest extends Specification{
    def "loading highscore"(){
        given:
            def scene = Mock(Scene)
            SceneViewer sceneViewer = new SceneViewer(scene)
            def menu = Mock(GameOverMenu)
            def controller = new GameOverMenuController(menu, 100000)
        when:
            int highScore = sceneViewer.loadHighScore()
        then:
            highScore == 100000
        cleanup:
             controller.saveHighScore(0)
    }
    def "loading highscore when the file is empty"(){
        given:
            def scene = Mock(Scene)
            SceneViewer sceneViewer = new SceneViewer(scene)
            GroovyMock(FileReader, global: true)
            GroovyMock(BufferedReader, global: true)
            def mockReader = Mock(BufferedReader)
            new FileReader(_) >> Mock(FileReader)
            new BufferedReader(_) >> mockReader
            mockReader.readLine() >> ""
            mockReader.close() >> {}
        when:
            int highScore = sceneViewer.loadHighScore()

        then:
            highScore == 0
    }
    def "should draw all elements"(){
        given:
            def scene = Mock(Scene)
            def gui = Mock(GUI)
            def time = System.currentTimeMillis()
            def player = Mock(Player)
            def pPos = new Vector(5, 5)
            def enemy1 = Mock(SpikeEnemy)
            def e1Pos = new Vector(5, 6)
            def enemy2 = Mock(NormalEnemy)
            def e2Pos = new Vector(5, 7)
            def enemy3 = Mock(ShellEnemy)
            def e3Pos = new Vector(6, 6)
            def bullet = Mock(Bullet)
            def bPos = new Vector(5, 9)
            def coin = Mock(Coin)
            def cPos = new Vector(5, 10)
            def wall = Mock(Wall)
            def wPos = new Vector(6, 7)
            def bWall = Mock(BreakableWall)
            def bwPos = new Vector(8, 8)
            def ccPos = new Vector(6, 5)
            Collectible collectible = new MaxHealthCollectible(ccPos, scene)
            player.getPosition() >> pPos
            enemy1.getPosition() >> e1Pos
            enemy2.getPosition() >> e2Pos
            enemy3.getPosition() >> e3Pos
            bullet.getPosition() >> bPos
            coin.getPosition() >> cPos
            wall.getPosition() >> wPos
            bWall.getPosition() >> bwPos
            scene.getPlayer() >> player
            scene.getBullets() >> [bullet]
            scene.getEnemies() >> [enemy1, enemy2, enemy3]
            scene.getCoins() >> [coin]
            scene.getWalls() >> [wall, bWall]
            scene.getCollectibles() >> [collectible]
            SceneViewer sceneViewer = new SceneViewer(scene)
        when:
            sceneViewer.drawElements(gui, time)
        then:
            0 * gui.drawText(new Vector(5, 10), 'O', '#D8A500')
    }

    def "test drawHud"(){
        given:
            def scene = Mock(Scene)
            def gui = Mock(GUI)
            def time = System.currentTimeMillis()
            def player = Mock(Player)
            def pPos = new Vector(5, 5)
            def enemy1 = Mock(SpikeEnemy)
            def e1Pos = new Vector(5, 6)
            def enemy2 = Mock(NormalEnemy)
            def e2Pos = new Vector(5, 7)
            def enemy3 = Mock(ShellEnemy)
            def e3Pos = new Vector(6, 6)
            def bullet = Mock(Bullet)
            def bPos = new Vector(5, 9)
            def coin = Mock(Coin)
            def cPos = new Vector(5, 10)
            def wall = Mock(Wall)
            def wPos = new Vector(6, 7)
            def bWall = Mock(BreakableWall)
            def bwPos = new Vector(8, 8)
            def ccPos = new Vector(6, 5)
            Collectible collectible = new MaxHealthCollectible(ccPos, scene)
            player.getPosition() >> pPos
            enemy1.getPosition() >> e1Pos
            enemy2.getPosition() >> e2Pos
            enemy3.getPosition() >> e3Pos
            bullet.getPosition() >> bPos
            coin.getPosition() >> cPos
            wall.getPosition() >> wPos
            bWall.getPosition() >> bwPos
            scene.getPlayer() >> player
            scene.getBullets() >> [bullet]
            scene.getEnemies() >> [enemy1, enemy2, enemy3]
            scene.getCoins() >> [coin]
            scene.getWalls() >> [wall, bWall]
            scene.getCollectibles() >> [collectible]
            SceneViewer sceneViewer = new SceneViewer(scene)
        when:
            sceneViewer.drawHud(gui)
        then:
            0 * gui.drawText(new Vector(5, 10), 'O', '#D8A500')
    }
}
