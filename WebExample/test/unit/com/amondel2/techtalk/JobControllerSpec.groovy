package com.amondel2.techtalk

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.TestFor
import spock.lang.Shared
import spock.lang.Specification
import com.amondel2.techtalk.ResponseClass

import org.springframework.http.HttpMethod

import groovy.json.JsonSlurper;
import groovy.util.slurpersupport.GPathResult

import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.servlet.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer

import spock.lang.Issue
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */


@TestFor(JobController)
class JobControllerSpec extends Specification {
    
    static respnseObj = new ResponseClass()
   
    def setup() {

    }

    def cleanup() {
    }

    void "test index"(){
	when:
	controller.index()
	then:
	response.redirectedUrl == '/job/list'
    }


    void "test List Call"() {
	given:
	def mockJobService = mockFor(JobService)
	mockJobService.demand.queryGet{def arg1 -> ['json':['name':'log']]}
	controller.jobService = mockJobService.createMock()
	when:
	controller.list()
	then:
	response.json.name == 'log'
    }
    
    void "test parent Call"() {
	given:
	def mockJobService = mockFor(JobService)
	mockJobService.demand.queryGet{def arg1 -> ['json':['name':'log']]}
	mockJobService.demand.transformResultForJtree{def arg1 -> ['name':'log']}
	controller.params.id = "123231"
	controller.jobService = mockJobService.createMock()
	when:
	controller.parent()
	then:
	response.json.name == 'log'
    }
    
    void "test delete Call"() {
	given:
	def mockJobService = mockFor(JobService)
	mockJobService.demand.deleteQuertStr{def arg1 -> ['json':['name':'log']]}
	controller.params.id = "123231"
	controller.jobService = mockJobService.createMock()

	when:
	controller.delete()
	then:
	response.json.message == 'Success'
    }
    
    void "test update Call"() {
	given:
	def mockJobService = mockFor(JobService)
	mockJobService.demand.update{def arg1 -> ['json':['name':'log']]}
	controller.params.id = "123231"
	controller.jobService = mockJobService.createMock()
	when:
	controller.update()
	then:
	response.json.message == 'Success'
    }
    
    void "test save Call"() {
	given:
	def mockJobService = mockFor(JobService)
	mockJobService.demand.save{def arg1 -> returnobj}
	controller.params.id = "123231"
	controller.jobService = mockJobService.createMock()
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
