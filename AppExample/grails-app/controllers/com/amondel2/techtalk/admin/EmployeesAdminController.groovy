package com.amondel2.techtalk.admin

import grails.rest.RestfulController;
import grails.rest.*;
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import com.amondel2.techtalk.Employees

class EmployeesAdminController extends RestfulController  {
    static responseFormats = ['html']
    def baseService
     static scaffold = Employees
     EmployeesAdminController() {
	 super(Employees)
     }
     
     @Override
     protected Employees createResource() {
	 Employees instance = new Employees()
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
