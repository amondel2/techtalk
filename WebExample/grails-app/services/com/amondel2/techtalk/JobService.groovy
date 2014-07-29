package com.amondel2.techtalk

import grails.transaction.Transactional
import static grails.async.Promises.*
import grails.async.*

@Transactional
class JobService {

    def transformResultForJtree(jsonResult) {
	def plist = new PromiseList()
	def results = []
	def transofrmResult = { node ->
	  [ 'id' : node.id, 'text': node.name, 'children' : node.employees?.size() > 0 ? true : false,childType: 'employee']
	}
	jsonResult.each{ it -> 
	    plist << transofrmResult(it)
	}
	plist.get()
    }
}
