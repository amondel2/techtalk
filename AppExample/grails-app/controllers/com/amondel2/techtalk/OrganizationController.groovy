package com.amondel2.techtalk

import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import grails.rest.RestfulController;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class OrganizationController extends RestfulController {
  static responseFormats = ['json', 'xml']
    OrganizationController() {
	    super(Organization)
    }
}