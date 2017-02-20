package com.amondel2.techtalk

import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class OrganizationDBController extends RestfulController {


    def orgService

    static allowedMethods = [findOrgByName:'GET',findOrgByExternalId:'GET',getAllSubOrgs:'GET',
        getJobs:'GET',getChildren:'GET',getTopLevelOrgs:'GET',getParent:'GET']
    static responseFormats = ['json', 'xml']
    OrganizationDBController() {
        super(Organization)
    }

    @Override
    protected Organization createResource() {
	def r =  request.JSON
        Organization instance = new Organization(r)
	instance.id = r.id
	instance
    }

    def getTopLevelOrgs() {
        respond orgService.topLevelOrgs(params.id)
    }

    def getJobs() {
        respond orgService.getJobs(params.id)
    }

    def getChildren() {
        respond orgService.getChildren(params.id)
    }

    def getAllSubOrgs() {
        respond orgService.getSubOrgs(params.id)
    }

    def getParent(){
        respond orgService.getParent(params.id)
    }

    def findOrgByExternalId() {
        respond orgService.findOrgByExternalId(params.id,params.externalId)
    }

    def findOrgByName() {
        respond orgService.findOrgByName(params.id,params.name)
    }
}