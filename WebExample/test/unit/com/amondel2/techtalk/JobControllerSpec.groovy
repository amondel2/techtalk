package com.amondel2.techtalk

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.TestFor
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
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(JobController)
class JobControllerSpec extends Specification {
    RestBuilder rest = new RestBuilder()
    def url = "http://localhost"
    MockRestServiceServer mockServer
    
    
    def setup() {
	grailsApplication.config.app.url = url
	mockServer = MockRestServiceServer.createServer(rest.restTemplate)
    }

    def cleanup() {
    }


    void "test List Call"() {
	given:
	mockServer.expect(requestTo(url + "/jobs"))
		.andExpect(method(HttpMethod.GET))
		.andExpect(header(HttpHeaders.ACCEPT, "application/json"))
		.andRespond(withSuccess('{"burt":"rendols"}', MediaType.APPLICATION_JSON))
	controller.rest = rest
	
	when:
	def testr = controller.list()
	then:
	mockServer.verify()
	response.text != null
	def t = new JsonSlurper().parseText(response.text)
	t.burt == 'rendols'
    }
}
