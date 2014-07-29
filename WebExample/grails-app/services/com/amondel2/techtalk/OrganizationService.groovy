package com.amondel2.techtalk

import com.sun.corba.se.spi.orbutil.closure.Closure;

import grails.transaction.Transactional
import static grails.async.Promises.*
import grails.async.*

@Transactional
class OrganizationService {

    def transformResultForJtree(jsonResult) {
	def plist = new PromiseList()
	def results = []
	def transofrmResult = { node ->
	  [ 'id' : node.id, 'text': node.name, 'children' : node.jobs?.size() > 0 ? true : false, childType: 'job']
	}
	jsonResult.each{ it -> 
	    plist << transofrmResult(it)
	}
	plist.get()
    }
}
