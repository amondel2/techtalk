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
	organizationService.update(params)
	render(contentType:"text/json"){["message":"Success"]}
    }
    
    def delete() {
	organizationService.deleteQuertStr("/organization/" + params?.id)
	render(contentType:"text/json"){["message":"Success"]}
    }
}