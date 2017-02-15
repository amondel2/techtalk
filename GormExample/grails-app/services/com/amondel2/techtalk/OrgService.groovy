package com.amondel2.techtalk

import grails.transaction.Transactional

@Transactional
class OrgService extends BaseService {

    static allowedMethods = [findOrgByExternalId:'POST',getSubOrgs:'GET',getJobs:'GET',getChildren:'GET',topLevelOrgs:'GET',getParent:'GET']

    def getSubOrgs(orgId) {
        def id = []
        Organization.findById(orgId).subOrgs?.each{ org ->
            id.push(org)
            if(org.subOrgs.size() > 0) {
                id + getSubOrgs(org.id)
            }
        }
        id
    }

    def findOrgByExternalId(companyId,externalString) {
        Organization.withCriteria{
            eq('company',Company{
                    eq('id',companyId)
                })
            ilike('orgUnitId','%' + externalString + '%')
        }
    }

    def getJobs(orgId) {
        OrgJobs.withCriteria{
            eq('org',Organization{
                    eq('id',orgId)
                })
        }
    }

    def getChildren(orgId) {
        Organization.findById(orgId).subOrgs
    }

    def topLevelOrgs(companyId) {
        Organization.findAllByCompanyAndParentIsNull(findCompany(companyId))
    }

    def getParent(orgId) {
        Organization.findById(orgId)?.parent
    }
}