package com.amondel2.techtalk

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(EmployeeController)
class EmployeeControllerSpec extends Specification {

    static respnseObj = new ResponseClass()
    
    def setup() {
    }

    def cleanup() {
    }

    void "test parent Call"() {
	given:
	def mockEmployeeService = mockFor(EmployeeService)
	mockEmployeeService.demand.queryGet{def arg1 -> ['json':['name':'log']]}
	mockEmployeeService.demand.transformResultForJtree{def arg1 -> ['name':'log']}
	controller.params.id = "123231"
	controller.employeeService = mockEmployeeService.createMock()
	when:
	controller.parent()
	then:
	response.json.name == 'log'
    }
    
    void "test delete Call"() {
	given:
	def mockEmployeeService = mockFor(EmployeeService)
	mockEmployeeService.demand.deleteQuertStr{def arg1 -> ['json':['name':'log']]}
	controller.params.id = "123231"
	controller.employeeService = mockEmployeeService.createMock()

	when:
	controller.delete()
	then:
	response.json.message == 'Success'
    }
    
    void "test update Call"() {
	given:
	def mockEmployeeService = mockFor(EmployeeService)
	mockEmployeeService.demand.update{def arg1 -> ['json':['name':'log']]}
	controller.params.id = "123231"
	controller.employeeService = mockEmployeeService.createMock()

	when:
	controller.update()
	then:
	response.json.message == 'Success'
    }
    
    void "test save Call"() {
	given:
	def mockEmployeeService = mockFor(EmployeeService)
	mockEmployeeService.demand.save{def arg1 -> returnobj}
	controller.params.id = "123231"
	controller.employeeService = mockEmployeeService.createMock()
	when:
	controller.save()
	then:
	response.json.message == resultText
	where:
	returnobj		|	resultText
	respnseObj.successText	|	'Success'
	respnseObj.successText2	|	'Success'
	respnseObj.failureText	|	'FAILURE'
	respnseObj.nullStatus	|	'FAILURE'
    }
}