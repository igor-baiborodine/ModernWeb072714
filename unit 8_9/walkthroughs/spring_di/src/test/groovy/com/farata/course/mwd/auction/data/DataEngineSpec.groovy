package com.farata.course.mwd.auction.data

import com.farata.course.mwd.auction.test.TestConfiguration
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.inject.Inject

/**
 * TODO
 *
 * @since 9/20/14
 * @author Viktor Gamov (viktor.gamov@faratasystems.com)
 */
@ContextConfiguration(classes = [
        TestConfiguration.class
])
class DataEngineSpec extends Specification {

    @Inject
    DataEngine dataEngine

    def "basic assumptions"() {
        expect:
        dataEngine
    }

    def "dataEngine should return 6 featured products"() {
        expect:
        dataEngine.findAllFeaturedProducts().size() == 6
    }
}
