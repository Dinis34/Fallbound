package Fallbound.GUI

import Fallbound.Model.Position
import spock.lang.Specification

class GUITest extends Specification {

    def "drawElement calls drawCharacter with correct parameters"() {
        given:
        def gui = Mock(GUI)
        def position = new Position(1, 1)
        def character = 'A' as char
        def color = "RED"

        when:
        gui.drawElement(position, character, color)

        then:
        1 * gui.drawElement(position, character, color)
    }

    def "drawText calls drawText with correct parameters"() {
        given:
        def gui = Mock(GUI)
        def position = new Position(1, 1)
        def text = "Hello"
        def color = "BLUE"

        when:
        gui.drawText(position, text, color)

        then:
        1 * gui.drawText(position, text, color)
    }

    def "clear calls clear method"() {
        given:
        def gui = Mock(GUI)

        when:
        gui.clear()

        then:
        1 * gui.clear()
    }

    def "refresh calls refresh method"() {
        given:
        def gui = Mock(GUI)

        when:
        gui.refresh()

        then:
        1 * gui.refresh()
    }

    def "close calls close method"() {
        given:
        def gui = Mock(GUI)

        when:
        gui.close()

        then:
        1 * gui.close()
    }

    def "getNextAction calls getNextAction method"() {
        given:
        def gui = Mock(GUI)

        when:
        gui.getNextAction()

        then:
        1 * gui.getNextAction()
    }

    def "getNextSingleAction calls getNextSingleAction method"() {
        given:
        def gui = Mock(GUI)

        when:
        gui.getNextSingleAction()

        then:
        1 * gui.getNextSingleAction()
    }
}