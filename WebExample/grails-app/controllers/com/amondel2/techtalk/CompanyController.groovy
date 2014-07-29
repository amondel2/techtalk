package com.amondel2.techtalk

class CompanyController {
    def grailsApplication
    def rest = new grails.plugins.rest.client.RestBuilder()
    def companyService

    def index() {
	def appURL = grailsApplication.config.app.url
	def  resp = rest.get(appURL + "/company") {
	    accept "application/json"
	}
	render(contentType:"text/json"){resp.json?.getAt(0)}
    }

    def jsTree() {
	if ( params.id && params.childType) {
	    redirect(controller: params.childType, action: "parent", params:params)
	} else {
	    def appURL = grailsApplication.config.app.url
	    def  resp = rest.get(appURL + "/company") {
		accept "application/json"
	    }
	    render(contentType:"text/json"){companyService.transformResultForJtree(resp.json)}
	}
    }

}