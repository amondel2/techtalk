package com.amondel2.techtalk

import grails.converters.JSON
import grails.web.JSONBuilder

class EmployeeService extends BaseService {

    def transformResultForJtree(jsonResult) {
	def transofrmResult = { node ->
	  [ 'id' : node.id, 'text': node.firstName + " " + node.lastName, 'children' :node.directReports?.size() > 0 ? true : false,childType: 'directReports']
	}
	super.transformResult(transofrmResult,jsonResult)
    }
    
    def update(params){
	def str = params.name?.split(' ')
	def builder = new JSONBuilder()
	JSON j = builder.build {
	    firstName = str[0]
	    lastName = str[1]
	    id = params.id
	}
	super.update(params,j,"employees")
    }
    
    
}
