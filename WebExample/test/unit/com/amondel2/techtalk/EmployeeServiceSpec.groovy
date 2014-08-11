package com.amondel2.techtalk

import org.springframework.test.web.client.MockRestServiceServer;

import grails.plugins.rest.client.RestBuilder;
import grails.test.mixin.TestFor
import spock.lang.Specification

import org.springframework.http.HttpMethod

import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.servlet.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(EmployeeService)
class EmployeeServiceSpec extends Specification {

    RestBuilder rest = new RestBuilder()
    def url = "http://localhost"
    MockRestServiceServer mockServer
    def setup() {
	grailsApplication.config.app.url = url
	mockServer = MockRestServiceServer.createServer(rest.restTemplate)
    }

    def cleanup() {
    }

    void "test transformResultForJtree Call"() {
	when:
	def rtn = service.transformResultForJtree(jsonResult)
	then:
	rtn == resultText
	where:
	jsonResult								|	resultText
	[['firstName':"Some", 'lastName': "Name",'id':"12313213",'directReports':['item']]] 	|	[['id':"12313213", 'text':"Some Name", 'children':true, 'childType':'directReports']]
	[['firstName':"",'lastName': "",'id':"12313213",'directReports':['item']]] 	|	[['id':"12313213", 'text':" ", 'children':true, 'childType':'directReports']]
	[['firstName':"Some", 'lastName': "Name",'id':"12313213",'directReports':[]]]	|	[['id':"12313213", 'text':"Some Name", 'children':false, 'childType':'directReports']]
	[['firstName':"Some", 'lastName': "Name",'id':"12313213"]]	|	[['id':"12313213", 'text':"Some Name", 'children':false, 'childType':'directReports']]
    }
    
    void "test update Call"() {
	given:
	mockServer.expect(requestTo(url + "/employees/" + jsonResult.id))
		.andExpect(method(HttpMethod.PUT))
		.andExpect(header(HttpHeaders.ACCEPT, "application/json"))
		.andRespond(withSuccess('{"burt":"dirt"}', MediaType.APPLICATION_JSON))
	service.rest = rest
	when:
	def rtn = service.update(jsonResult)
	then:
	rtn.json == resultText
	where:
	jsonResult | resultText
	['name': 'joe dirt', 'id':'32432432'] | ["burt":"dirt"]
    }
    
    void "test save Call"() {
	given:
	mockServer.expect(requestTo(url + "/employees/"))
	       .andExpect(method(HttpMethod.POST))
	       .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
	       .andRespond(withSuccess('{"burt":"rendols"}', MediaType.APPLICATION_JSON))
       service.rest = rest
	when:
	def rtn = service.save(jsonResult)
	then:
	rtn.json == resultText
	where:
	jsonResult | resultText
	['name':'joe dirt','parentId':'32432432'] | ["burt":"rendols"]
    }
}
