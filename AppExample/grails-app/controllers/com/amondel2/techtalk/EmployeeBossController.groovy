package com.amondel2.techtalk
import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class EmployeeBossController extends RestfulController{
    static responseFormats = ['json', 'xml']
    EmployeeBossController() {
        super(EmployeeBoss)
    }
}
