package com.amondel2.techtalk
import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class EmployeeBossDBController extends RestfulController{
    static responseFormats = ['json', 'xml']
    EmployeeBossDBController() {
        super(EmployeeBoss)
    }
}