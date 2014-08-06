package com.amondel2.techtalk

import java.util.ArrayList;

import grails.converters.JSON
import grails.web.JSONBuilder
import grails.plugins.rest.client.RestBuilder
import static grails.async.Promises.*
import grails.async.*

class BaseService {
    def grailsApplication
    def rest = new RestBuilder()
    
    def update(params,j,controller){
	def appURL = grailsApplication.config.app.url + "/" + controller + "/" + params.id
	return rest.put(appURL) {
	     contentType "application/json"
	     json j
	 }
    }
        
    def save(j,controller){
	def appURL = grailsApplication.config.app.url + "/" + controller  + "/"
	return rest.post(appURL) {
	     contentType "application/json"
	     json j
	 }
    }
    
    def deleteQuertStr(qString) {
	return rest.delete(grailsApplication.config.app.url + qString) {
	    accept "application/json"
	}
    }
    
    def queryGet(qString) {
	return rest.get(grailsApplication.config.app.url + qString) {
	    accept "application/json"
	}
    }
    
    def transformResult(transofrmResult,jsonResult) {
	def plist = new PromiseList()
	def results = []
	jsonResult.each{ it ->
	    plist << transofrmResult(it)
	}
	plist.get()
    }
}
