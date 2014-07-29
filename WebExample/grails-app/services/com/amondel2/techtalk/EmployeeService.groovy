package com.amondel2.techtalk

import grails.transaction.Transactional
import static grails.async.Promises.*
import grails.async.*

@Transactional
class EmployeeService {

    def transformResultForJtree(jsonResult) {
	def plist = new PromiseList()
	def results = []
	def transofrmResult = { node ->
	  [ 'id' : node.id, 'text': node.firstName + " " + node.lastName, 'children' :node.directReports?.size() > 0 ? true : false,childType: 'directReports']
	}
	jsonResult.each{ it -> 
	    plist << transofrmResult(it)
	}
	plist.get()
    }
}
