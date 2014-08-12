package com.fib

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(FibonacciService)
class FibonacciServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test static better than dynamic"() {
	given:
            def index = a
            def result
        when:
            result = service.fib(index)
        then:
            result['dynamic'] < result['static']
        where:
            a << [50000,100000,200000,500000]
    }
}
