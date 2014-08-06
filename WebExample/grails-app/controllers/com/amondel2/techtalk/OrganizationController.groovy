package com.amondel2.techtalk
import grails.converters.JSON
import grails.web.JSONBuilder

class OrganizationController {
    def organizationService
   
    
    def parent(){
	def  resp = organizationService.queryGet("/organization")
	render(contentType:"text/json"){organizationService.transformResultForJtree(resp.json)}
    }
    
    def update() {
	def p = request.JSON
	p.id = params.id 
	organizationService.update(p)
	render(contentType:"text/json"){["message":"Success"]}
    }
    
    def delete() {
	organizationService.deleteQuertStr("/organization/" + params?.id)
	render(contentType:"text/json"){["message":"Success"]}
    }
    
    def save() {
	def rtn = organizationService.save(request.JSON)
	def status = rtn?.getStatus()
	def result = "FAILURE"
	if(status == 200 || status == 201) {
	    result = "Success"
	}
	def body = rtn?.json
	
	render(contentType:"text/json"){["message":result,results:body,childType: 'job']}
    }
}