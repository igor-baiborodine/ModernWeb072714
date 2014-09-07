import spock.lang.Specification
import spock.lang.Unroll

class DataDriven extends Specification {
    def "maximum of two numbers"() {
        expect:
        Math.max(a, b) == c

        where:
        a << [3, 5, 9]
        b << [7, 4, 9]
        c << [7, 5, 9]
    }

    @Unroll("minimum of #a and #b is #c")
    def "minimum of two numbers"() {
        expect:
        Math.min(a, b) == c

        where:
        [a, b, c] << [[3, 7, 3], [5, 4, 4], [9, 9, 9]]
    }
}