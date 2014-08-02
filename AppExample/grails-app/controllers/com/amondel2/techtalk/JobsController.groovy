package com.amondel2.techtalk

import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class JobsController extends RestfulController {
	static responseFormats = ['json', 'xml']
	JobsController() {
		super(Jobs)
	}
	
	def parent() {  
	   respond  Jobs.findAllByOrganization(Organization.findAllById(params?.id))
	}
}