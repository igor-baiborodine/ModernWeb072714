package com.farata.course.mwd.auction.service

import com.farata.course.mwd.auction.test.TestConfiguration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import spock.lang.Specification

import javax.inject.Inject

/**
 * TODO
 *
 * @since 9/21/14
 * @author Viktor Gamov (viktor.gamov@faratasystems.com)
 */
@ContextConfiguration(classes = [
        TestConfiguration.class
],loader = AnnotationConfigContextLoader.class)
class ProductServiceSpec extends Specification {
    @Inject
    ProductService productService

    def "basic assumptions"() {
        expect:
        productService
    }
}
