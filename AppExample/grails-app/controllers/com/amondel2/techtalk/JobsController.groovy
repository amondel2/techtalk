package com.amondel2.techtalk
import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*

class JobsController extends RestfulController {
	def scaffold=true
	static responseFormats = ['json', 'xml']
	JobsController() {
		super(Jobs)
	} 
	


}
