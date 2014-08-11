package com.amondel2.techtalk.admin

import grails.rest.RestfulController;
import grails.rest.*;
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
}
