package com.amondel2.techtalk
import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CompanyController extends RestfulController{
    static responseFormats = ['json', 'xml']
    CompanyController() {
	    super(Company)
    }
}
