package com.amondel2.techtalk
import grails.rest.RestfulController

import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class CompanyDBController extends RestfulController {

    static responseFormats = ['json', 'xml']

    def companyService
    def orgService

    static allowedMethods = [findOrgByExternalId:'POST',getSubOrgs:'GET',getAllSubOrgs:'GET']

    CompanyDBController() {
        super(Company)
    }

    def getSubOrgs() {
        respond companyService.getChildren(params.id)
    }

    def getAllSubOrgs() {
        respond companyService.getSubOrgs(params.id)
    }

    def findOrgByExternalId() {
        respond orgService.findOrgByExternalId(params.id,params.externalId)
    }
}