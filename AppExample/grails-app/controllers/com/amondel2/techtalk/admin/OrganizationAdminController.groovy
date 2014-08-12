package com.amondel2.techtalk.admin

import grails.rest.*;
import grails.transaction.Transactional;
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

import com.amondel2.techtalk.Organization

class OrganizationAdminController extends RestfulController  {
    static responseFormats = ['html']
    
     static scaffold = Organization
    
     def baseService
      
     OrganizationAdminController() {
	 super(Organization)
     }
     
     @Override
     protected Organization createResource() {
	 Organization instance = new Organization()
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
 
	 Organization instance = queryForResource(params.id)
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
