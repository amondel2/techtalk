package com.amondel2.techtalk.admin
import com.amondel2.techtalk.Company

import grails.rest.RestfulController
import grails.rest.*;
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
    protected Company createResource() {
	Company instance = new Company()
	bindData instance, getObjectToBind()
	try{
	    if( instance.hasProperty('id') && ( instance.id == null ||  instance.id == '' ||  instance.id.trim().size() == 0)) {
		instance.id = baseService.generateGuid()
	    }
	} catch (Exception e) {

	}
	instance
    }
}