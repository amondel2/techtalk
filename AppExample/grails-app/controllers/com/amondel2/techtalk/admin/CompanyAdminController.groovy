package com.amondel2.techtalk.admin
import com.amondel2.techtalk.Company

import grails.rest.*;
import grails.transaction.Transactional;
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class CompanyAdminController extends RestfulController {
    static responseFormats = ['html']
    static scaffold = Company

    def baseService
    
    CompanyAdminController() {
	super(Company)
    }
    
    @Override
    def update() {
	if(handleReadOnly()) {
	    return
	}

	Company instance = queryForResource(params.id)
	if (instance == null) {
	    notFound()
	    return
	}

	instance.properties = getObjectToBind()

	if (instance.hasErrors()) {
	    respond instance.errors, view:'edit' // STATUS CODE 422
	    return
	}

	instance.save flush:true
	redirect(controller:controllerName, action: 'show',id: instance.id)
    }
    
    
    
}