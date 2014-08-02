package com.amondel2.techtalk

import grails.converters.JSON
import grails.web.JSONBuilder

class JobService extends BaseService {

    def transformResultForJtree(jsonResult) {
	def transofrmResult = { node ->
	    [ 'id' : node.id, 'text': node.name, 'children' : node.employees?.size() > 0 ? true : false,childType: 'employee']
	}
	super.transformResult(transofrmResult,jsonResult)
    }

    def update(params){
	def builder = new JSONBuilder()
	JSON j = builder.build {
	    name = params.name
	    id = params.id
	}
	super.update(params,j,"jobs")
    }
}
