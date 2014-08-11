package com.amondel2.techtalk

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CompanyController)
class CompanyControllerSpec extends Specification {
    static respnseObj = new ResponseClass()

    def setup() {
    }

    def cleanup() {
    }

    void "test index"(){
	given:
	def mockCompanyService = mockFor(OrganizationService)
	mockCompanyService.demand.queryGet{def arg1 -> ['json':[['name':'log']]]}
	controller.companyService = mockCompanyService.createMock()
	when:
	controller.index()
	then:
	response.json.name == 'log'
    }


    void "test jsTree"(){
	given:
	def mockCompanyService = mockFor(OrganizationService)
	mockCompanyService.demand.queryGet{def arg1 -> ['json':[['name':'log']]]}
	mockCompanyService.demand.transformResultForJtree{def arg1 -> ['name':'log']}
	controller.companyService = mockCompanyService.createMock()
	myParams.each {
	    controller.params[it.key] = it.value
	}
	when:
	controller.jsTree()
	then:
	if(myParams.childType && myParams.id)
	    response.redirectUrl == '/' + params.childType + '/parent'
	else
	    response.json.name == resultText
	where:
	myParams					| resultText   
	['id':'123213','childType':'jobs']	| ''
	['id':'','childType':'jobs']		| 'log'
	['id':'123213','childType':'']		| 'log'	
	['id':null,'childType':null]		| 'log'
	[]					| 'log'
	['id':'123213']				| 'log'
	['childType':'jobs']			| 'log'
	
	
    }
    
    void "test delete Call"() {
	when:
	controller.delete()
	then:
	response.json.message == 'FAILURE'
    }

  
      void "test update Call"() {
	given:
	def mockCompanyService = mockFor(CompanyService)
	mockCompanyService.demand.update{def arg1 -> ['json':['name':'log']]}
	controller.params.id = "123231"
	controller.companyService = mockCompanyService.createMock()

	when:
	controller.update()
	then:
	response.json.message == 'Success'
    }
}
