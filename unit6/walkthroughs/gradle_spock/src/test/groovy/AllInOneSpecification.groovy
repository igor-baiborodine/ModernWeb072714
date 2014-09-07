import spock.lang.Specification

class AllInOneSpecification extends Specification {

    def setupSpec() {
        println "// run before the first feature method"
    }

    def setup() {
        println "// run before every feature method"
    }

    def "color should be green"() {
        setup:
        println "setup in spec"
        def color = "green";

        expect:
        color == "green";

        cleanup:
        println "wiping the dust"
    }

    def "another color spec"() {
        given:
        println "given is the same as setup"
        expect:
        1 == 1
    }

    def cleanup() {
        println "// run after every feature method"
    }

    def cleanupSpec() {
        println "// run after the last feature method"
    }

}