package com.amondel2.techtalk

class JobController {
    def grailsApplication
    def rest = new grails.plugins.rest.client.RestBuilder()
    def jobService
    
    def index() {redirect(action: "list")}
    def list(){
	def url = grailsApplication.config.app.url
	def  resp = rest.get(grailsApplication.config.app.url + "/jobs") {
	    accept "application/json"

	}
	render(contentType:"text/json"){resp.json}
    }
    
    def parent() {
	def  resp = rest.get(grailsApplication.config.app.url + "/jobs/parent/" + params.id) {
	    accept "application/json"
	}
	render(contentType:"text/json"){jobService.transformResultForJtree(resp.json)}
    }
}
