package com.amondel2.techtalk.admin

import grails.rest.RestfulController;
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
    
    
}