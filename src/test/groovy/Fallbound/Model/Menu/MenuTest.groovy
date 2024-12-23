package Fallbound.Model.Menu

import spock.lang.Specification

class MenuTest extends Specification {

    def "Next Option"() {
        given:
        def menu = new StartMenu()

        when:
        menu.nextOption()

        then:
        menu.isSelected(1)
    }

    def "Next Option selected >= options.size()"() {
        given:
        def menu = new StartMenu()
        menu.selected = 3

        when:
        menu.nextOption()

        then:
        menu.isSelected(0)
    }

    def "Previous Option"() {
        given:
        def menu = new StartMenu()
        menu.selected = 1

        when:
        menu.previousOption()

        then:
        menu.isSelected(0)
    }

    def "Previous Option selected < 0"() {
        given:
        def menu = new StartMenu()
        menu.selected = 0

        when:
        menu.previousOption()

        then:
        menu.isSelected(1)
    }
}
