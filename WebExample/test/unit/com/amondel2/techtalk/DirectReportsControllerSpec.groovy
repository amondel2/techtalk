package com.amondel2.techtalk

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DirectReportsController)
class DirectReportsControllerSpec extends Specification {

	def setup() {
	}

	def cleanup() {
	}

	void "test parent Call"() {
	given:
	def mockJobService = mockFor(EmployeeService)
	mockJobService.demand.queryGet{def arg1 -> ['json':['name':'log']]}
	mockJobService.demand.transformResultForJtree{def arg1 -> ['name':'log']}
	controller.params.id = "123231"
	controller.employeeService = mockJobService.createMock()
	when:
	controller.parent()
	then:
	response.json.name == 'log'
    }
}
