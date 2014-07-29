package com.amondel2.techtalk

import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EmployeesController extends RestfulController {
   
    static responseFormats = ['json', 'xml']
    EmployeesController() {
	    super(Employees)
    }
    
    def parent() {
	respond Employees.findAllByJob(Jobs.findAllById(params?.id))
     }
    
    def directReports() {
//	respond Employees.findAllById(params?.id).directReports
	respond Employees.findAllByBoss(Employees.findAllById(params?.id))
    }
}
