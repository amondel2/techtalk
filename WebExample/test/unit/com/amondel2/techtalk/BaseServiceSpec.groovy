package com.amondel2.techtalk

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.TestFor
import grails.web.JSONBuilder
import spock.lang.Specification

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
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(BaseService)
class BaseServiceSpec extends Specification {

    RestBuilder rest = new RestBuilder()
    def url = "http://localhost"
    MockRestServiceServer mockServer
    
   static Closure transofrmResult = { node ->
	[ 'id' : node.id, 'text': node.name, 'children' : node.employees?.size() > 0 ? true : false,childType: 'employee']
    }
    def setup() {
	grailsApplication.config.app.url = url
	mockServer = MockRestServiceServer.createServer(rest.restTemplate)
    }

    def cleanup() {
    }

   void "test queryGet Call"() {
	given:
	def qstring = "/jobs"
	mockServer.expect(requestTo(url + qstring))
		.andExpect(method(HttpMethod.GET))
		.andExpect(header(HttpHeaders.ACCEPT, "application/json"))
		.andRespond(withSuccess('{"burt":"rendols"}', MediaType.APPLICATION_JSON))
	service.rest = rest
	
	when:
	def testr = service.queryGet(qstring)
	then:
	mockServer.verify()
	testr.json.burt == 'rendols'
    }
   
   void "test deleteQuertStr call"() {
       given:
       def qstring = "/jobs"
       mockServer.expect(requestTo(url + qstring))
	       .andExpect(method(HttpMethod.DELETE))
	       .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
	       .andRespond(withSuccess('{"burt":"rendols"}', MediaType.APPLICATION_JSON))
       service.rest = rest
       when:
       def testr = service.deleteQuertStr(qstring)
       then:
       mockServer.verify()
       testr.json.burt == 'rendols'
   }
   
   
   void "test save call"() {
       given:
       def builder = new JSONBuilder()
       JSON j = builder.build {
	   name = "Some Name"
	   parentId =  "12313213"
       }
       def controller = "jobs"
       mockServer.expect(requestTo(url + "/" + controller  + "/"))
	       .andExpect(method(HttpMethod.POST))
	       .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
	       .andRespond(withSuccess('{"burt":"rendols"}', MediaType.APPLICATION_JSON))
       service.rest = rest
       when:
       def testr = service.save(j,controller)
       then:
       mockServer.verify()
       testr.json.burt == 'rendols'
   }
   
   void "test update call"() {
       given:
       def builder = new JSONBuilder()
       JSON j = builder.build {
	   name = "Some Name"
	   parentId =  "12313213"
       }
       def controller = "jobs"
       def params = ['id':'12313445']
       mockServer.expect(requestTo(url + "/" + controller  + "/" + params.id))
	       .andExpect(method(HttpMethod.PUT))
	       .andExpect(header(HttpHeaders.ACCEPT, "application/json"))
	       .andRespond(withSuccess('{"burt":"rendols"}', MediaType.APPLICATION_JSON))
       service.rest = rest
       when:
       def testr = service.update(params,j,controller)
       then:
       mockServer.verify()
       testr.json.burt == 'rendols'
   }
   
   void "test transformResult call"() {
       given:
       
       def controller = "jobs"
       def params = ['id':'12313445']
      
       when:
       def testr = service.transformResult(transofrmResultparam,jsonResult)
       then:
       testr.size() == 1
       where:
       transofrmResultparam	|	jsonResult 							
       transofrmResult		|	[['name':"Some Name",'id':"12313213",'employees':['item']]] 	
       transofrmResult         	|	[['name':"Some Name",'id':"12313213",'employees':[]]]
       transofrmResult		|	[['name':"Some Name",'id':"12313213"]]
   }
   
}
