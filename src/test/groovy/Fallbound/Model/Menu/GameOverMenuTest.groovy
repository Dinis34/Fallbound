package Fallbound.Model.Menu

import spock.lang.Specification

class GameOverMenuTest extends Specification {

    def "isSelectedRestart is true"(){
        given:
        def menu = Spy(GameOverMenu)

        when:
        menu.isSelected(0) >> true

        then:
        menu.isSelectedRestart()
    }

    def "isSelectedRestart is false"(){
        given:
        def menu = Spy(GameOverMenu)

        when:
        menu.isSelected(0) >> false

        then:
        !menu.isSelectedRestart()
    }

    def "isSelectedExit is true"(){
        given:
        def menu = Spy(GameOverMenu)

        when:
        menu.isSelected(1) >> true

        then:
        menu.isSelectedExit()
    }

    def "isSelectedExit is false"(){
        given:
        def menu = Spy(GameOverMenu)

        when:
        menu.isSelected(1) >> false

        then:
        !menu.isSelectedExit()
    }

    def "isSelectedExitToDesktop is true"(){
        given:
        def menu = Spy(GameOverMenu)

        when:
        menu.isSelected(2) >> true

        then:
        menu.isSelectedExitToDesktop()
    }

    def "isSelectedExitToDesktop is false"(){
        given:
        def menu = Spy(GameOverMenu)

        when:
        menu.isSelected(2) >> false

        then:
        !menu.isSelectedExitToDesktop()
    }

    def "isNewHighScore is true"(){
        given:
        def menu = new GameOverMenu()

        when:
        menu.setNewHighScore(true)

        then:
        menu.isNewHighScore()
    }

    def "isNewHighScore is false"(){
        given:
        def menu = new GameOverMenu()

        when:
        menu.setNewHighScore(false)

        then:
        !menu.isNewHighScore()
    }
}
