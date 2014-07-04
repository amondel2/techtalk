package com.amondel2.techtalk

class JobController {
	def grailsApplication
	def rest = new grails.plugins.rest.client.RestBuilder()
	
    def index() {redirect(action: "list")}
	def list(){
		def  resp = rest.get(grailsApplication.config.app.url + "/jobs")
		render(contentType:"text/json"){resp.json}
	}
}
 