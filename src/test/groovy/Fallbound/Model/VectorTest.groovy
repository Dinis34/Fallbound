package Fallbound.Model

import spock.lang.Specification

class VectorTest extends Specification {

    def "subtract vectors"() {
        given:
        def vector1 = new Vector(3.0, 4.0)
        def vector2 = new Vector(1.0, 2.0)

        when:
        def result = vector1.subtract(vector2)

        then:
        result.getX() == 2.0
        result.getY() == 2.0
    }

    def "subtract vectors with negative result"() {
        given:
        def vector1 = new Vector(1.0, 1.0)
        def vector2 = new Vector(2.0, 2.0)

        when:
        def result = vector1.subtract(vector2)

        then:
        result.getX() == -1.0
        result.getY() == -1.0
    }

    def "toString format"() {
        given:
        def vector = new Vector(3.0, 4.0)

        expect:
        vector.toString() == "Vector{x=3.0, y=4.0}"
    }

    def "toString with negative values"() {
        given:
        def vector = new Vector(-1.0, -5.0)

        expect:
        vector.toString() == "Vector{x=-1.0, y=-5.0}"
    }

    def "equals true"() {
        given:
        def vector1 = new Vector(2.0, 3.0)
        def vector2 = new Vector(2.0, 3.0)

        when:
        def result = vector1.equals(vector2)

        then:
        result
    }

    def "equals false different values"() {
        given:
        def vector1 = new Vector(1.0, 2.0)
        def vector2 = new Vector(3.0, 4.0)

        when:
        def result = vector1.equals(vector2)

        then:
        !result
    }

    def "equals false different class"() {
        given:
        def vector = new Vector(2.0, 3.0)
        def other = new Object()

        when:
        def result = vector.equals(other)

        then:
        !result
    }

    def "equals same object"() {
        given:
        def vector = new Vector(2.0, 3.0)

        when:
        def result = vector.equals(vector)

        then:
        result
    }

    def "hashCode is equal for equal vectors"() {
        given:
        def vector1 = new Vector(1.0, 2.0)
        def vector2 = new Vector(1.0, 2.0)

        expect:
        vector1.hashCode() == vector2.hashCode()
    }

    def "hashCode is different for different vectors"() {
        given:
        def vector1 = new Vector(1.0, 2.0)
        def vector2 = new Vector(3.0, 4.0)

        expect:
        vector1.hashCode() != vector2.hashCode()
    }
}
