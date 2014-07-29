package com.amondel2.techtalk


class OrganizationController {
    def grailsApplication
    def organizationService
    def jobService
    def rest = new grails.plugins.rest.client.RestBuilder()

    def index() {redirect(action: "list", params:params)}
    def parent(){
	def url = grailsApplication.config.app.url
	def  resp = rest.get(grailsApplication.config.app.url + "/organization") {
	    accept "application/json"
	}
	render(contentType:"text/json"){organizationService.transformResultForJtree(resp.json)}
    }
}