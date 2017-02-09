package com.amondel2.techtalk

import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class OrganizationController extends RestfulController {

    def baseService
    def orgService
    static responseFormats = ['json', 'xml']
    OrganizationController() {
        super(Organization)
    }

    @Override
    protected Organization createResource() {
	def r =  request.JSON
        Organization instance = new Organization(r)
	instance.id = r.id
	instance
    }

    def getBaseOrgs() {
        respond Organization.findAllByCompanyAndParentIsNull(Company.findById(params.id))
    }

    def subOrgs() {
        respond Organization.findById(params.id).subOrgs
    }

    def getAllSubOrgIds() {
        respond orgService.getSubOrgs(params.id)
    }

}