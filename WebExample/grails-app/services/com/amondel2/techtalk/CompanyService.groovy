package com.amondel2.techtalk

import grails.converters.JSON
import grails.web.JSONBuilder

class CompanyService extends BaseService {    
    def transformResultForJtree(jsonResult) {
	def transofrmResult = { node ->
	  [ 'id' : node.id, 'text': node.name, 'children' :node.organizations?.size() > 0 ? true : false,childType: 'organization']
	}
	super.transformResult(transofrmResult,jsonResult)
    }
         
    def update(params){
	def builder = new JSONBuilder()
	JSON j = builder.build {
	    name = params.name
	    id = params.id
	}
	super.update(params,j,"company")
    }
}