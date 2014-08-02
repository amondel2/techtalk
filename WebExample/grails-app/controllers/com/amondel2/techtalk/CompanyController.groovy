package com.amondel2.techtalk

class CompanyController {
    def companyService

    def index() {
	def  resp = companyService.queryGet("/company")
	render(contentType:"text/json"){resp.json?.getAt(0)}
    }

    def jsTree() {
	if ( params.id && params.childType) {
	    redirect(controller: params.childType, action: "parent", params:params)
	} else {
	    def resp = companyService.queryGet("/company")
	    render(contentType:"text/json"){companyService.transformResultForJtree(resp.json)}
	}
    }

    def update() {
	companyService.update(params)
	render(contentType:"text/json"){["message":"Success"]}
    }
    
    def delete() {
	render(contentType:"text/json"){["message":"FAILURE"]} 
    }

}