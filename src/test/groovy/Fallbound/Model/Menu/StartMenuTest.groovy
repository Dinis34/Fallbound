package Fallbound.Model.Menu

import spock.lang.Specification

class StartMenuTest extends Specification {

    def "isSelectedPlay is True"() {
        given:
        def menu = Spy(StartMenu)

        when:
        menu.isSelected(0) >> true

        then:
        menu.isSelectedPlay()
    }

    def "isSelectedPlay is false"() {
        given:
        def menu = Spy(StartMenu)

        when:
        menu.isSelected(0) >> false

        then:
        !menu.isSelectedPlay()

    }

    def "isSelectedExit is true"() {
        given:
        def menu = Spy(StartMenu)

        when:
        menu.isSelected(1) >> true

        then:
        menu.isSelectedExit()
    }

    def "isSelectedExit is false"() {
        given:
        def menu = Spy(StartMenu)

        when:
        menu.isSelected(1) >> false

        then:
        !menu.isSelectedExit()
    }
}
