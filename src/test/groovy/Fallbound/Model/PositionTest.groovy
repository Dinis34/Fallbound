package Fallbound.Model

import spock.lang.Specification

class PositionTest extends Specification {

    def "equals true"() {
        given:
        def pos1 = new Position(2, 3)
        def pos2 = new Position(2, 3)

        when:
        def result = pos1.equals(pos2)

        then:
        result
    }

    def "equals false different values"() {
        given:
        def pos1 = new Position(2, 3)
        def pos2 = new Position(3, 4)

        when:
        def result = pos1.equals(pos2)

        then:
        !result
    }

    def "equals false different class"() {
        given:
        def position = new Position(2, 2)
        def other = new Object()

        expect:
        !position.equals(other)
    }

    def "equals same object"() {
        given:
        def position = new Position(2, 2)

        expect:
        position.equals(position)
    }

    def "toString format"() {
        given:
        def position = new Position(3, 4)

        expect:
        position.toString() == "(3, 4)"
    }

    def "toString with negative values"() {
        given:
        def position = new Position(-1, -5)

        expect:
        position.toString() == "(-1, -5)"
    }
}
