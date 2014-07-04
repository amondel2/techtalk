package com.amondel2.techtalk

class JobController {
	def grailsApplication
    def index() {redirect(action:"list",params:parmas)}
	def list(){
		def  resp = rest.get(grailsApplication.app.url + "/jobs")
		render(contentType:"text/json"){resp.json}		
		
	}
}
 