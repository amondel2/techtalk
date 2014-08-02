package com.amondel2.techtalk
import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class CompanyController extends RestfulController{
    static responseFormats = ['json', 'xml']
    CompanyController() {
	    super(Company)
    }
}