package Fallbound.View.Game

import Fallbound.Controller.Menu.GameOverMenuController
import Fallbound.Model.Game.Scene
import Fallbound.Model.Menu.GameOverMenu
import spock.lang.Specification

class SceneViewerTest extends Specification {
    def "loading highscore"() {
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

    def "loading highscore when the file is empty"() {
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
}
