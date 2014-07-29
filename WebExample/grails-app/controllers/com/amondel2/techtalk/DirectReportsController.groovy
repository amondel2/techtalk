package com.amondel2.techtalk

class DirectReportsController {

    def grailsApplication
    def rest = new grails.plugins.rest.client.RestBuilder()
    def employeeService
    
    def index() { }
    
    def parent() {
	def  resp = rest.get(grailsApplication.config.app.url + "/employees/directReports/" + params.id) {
	    accept "application/json"
	}
	render(contentType:"text/json"){employeeService.transformResultForJtree(resp.json)}
    }
}
