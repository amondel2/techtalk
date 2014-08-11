package com.amondel2.techtalk

import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class OrganizationController extends RestfulController {
    
  def baseService
    
  static responseFormats = ['json', 'xml']
    OrganizationController() {
	    super(Organization)
    }
    
    @Override
    protected Organization createResource() {
	def r =  request.JSON
        Organization instance = new Organization(r)
	instance.id = r.id
	instance
    }
}