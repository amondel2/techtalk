package com.amondel2.techtalk

import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class EmployeesController extends RestfulController {
   
    static responseFormats = ['json', 'xml']
    EmployeesController() {
	    super(Employees)
    }
    
    def parent() {
	respond Employees.findAllByJob(Jobs.findAllById(params?.id))
     }
    
    def directReports() {
	respond Employees.findAllByBoss(Employees.findAllById(params?.id))
    }
    
    @Override
    protected Employees createResource() {
	def r =  request.JSON
	Employees instance = new Employees(r)
	instance.gender = instance.gender ?: "Male"
	instance.id = r.id
	instance
    }
}
