package com.amondel2.techtalk

import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class EmployeesDBController extends RestfulController {

    static responseFormats = ['json', 'xml']

    def employeeService

    EmployeesDBController() {
        super(Employees)
    }

    def getBosses() {
        respond employeeService.getBosses(params?.id)
    }

    def getJob() {
	respond employeeService.getJob(params.id)
    }

    def directReports() {
	respond employeeService.getDirectReports(params.id)
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
