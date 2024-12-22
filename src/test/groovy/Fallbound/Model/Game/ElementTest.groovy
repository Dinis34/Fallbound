package Fallbound.Model.Game

import Fallbound.Model.Game.Elements.Element
import Fallbound.Model.Vector
import spock.lang.Specification

class ElementTest extends Specification {

    def "Element should get and set position correctly"() {
        given:
        def position = Mock(Vector)
        def newPosition = Mock(Vector)
        def element = new Element(position)

        expect:
        element.getPosition() == position

        when:
        element.setPosition(newPosition)

        then:
        element.getPosition() == newPosition
    }

    def "Element equals should return true for elements with the same position"() {
        given:
        def position = new Vector(1, 2)
        def anotherPosition = new Vector(1, 2)
        def differentPosition = new Vector(2, 3)

        and:
        def element1 = new Element(position)
        def element2 = new Element(anotherPosition)
        def element3 = new Element(differentPosition)

        expect:
        element1.equals(element1)
        element1.equals(element2)
        !element1.equals(element3)
        !element1.equals(null)
        !element1.equals("not an element")
    }

    def "Element hashCode should match the hashCode of its position"() {
        given:
        def position = Mock(Vector)

        when:
        def element = new Element(position)

        and:
        position.hashCode() >> 12345

        then:
        element.hashCode() == 12345
    }
}
