package com.amondel2.techtalk.admin

import grails.rest.RestfulController;
import grails.transaction.Transactional;
import static org.springframework.http.HttpMethod.*

import com.amondel2.techtalk.Jobs

class JobsAdminController extends RestfulController  {
    static responseFormats = ['html']
    static scaffold= Jobs
    def baseService
    JobsAdminController() {
	super(Jobs)
    }
    
    @Override
    protected Jobs createResource() {
	Jobs instance = new Jobs()
	bindData instance, getObjectToBind()
	try{
	    if( instance.hasProperty('id') && ( instance.id == null ||  instance.id == '' ||  instance.id.trim().size() == 0)) {
		instance.id = baseService.generateGuid()
	    }
	} catch (Exception e) {

	}
	instance
    }
    
    @Override
    def update() {
	if(handleReadOnly()) {
	    return
	}

	Jobs instance = queryForResource(params.id)
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
    
    @Transactional
    def save() {
	if(handleReadOnly()) {
	    return
	}
	def instance = createResource()

	instance.validate()
	if (instance.hasErrors()) {
	    respond instance.errors, view:'create' // STATUS CODE 422
	    return
	}

	instance.save flush:true
	redirect(controller:controllerName, action: 'show',id: instance.id)
    }
    
    
}