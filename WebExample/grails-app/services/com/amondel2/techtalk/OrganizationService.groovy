package com.amondel2.techtalk

import grails.converters.JSON
import grails.web.JSONBuilder

class OrganizationService extends BaseService {

    def transformResultForJtree(jsonResult) {
	def transofrmResult = { node ->
	    [ 'id' : node.id, 'text': node.name, 'children' : node.jobs?.size() > 0 ? true : false, childType: 'job']
	}
	super.transformResult(transofrmResult,jsonResult)
    }

    def update(params){
	def builder = new JSONBuilder()
	JSON j = builder.build {
	    name = params.name
	    id = params.id
	}
	super.update(params,j,"organization")
    }
    
    def save(params) {
	def builder = new JSONBuilder()
	JSON j = builder.build {
	    name = params.name
	    parentId =  params.parentId
	}
	super.save(j,"organization")
    }
   
}
