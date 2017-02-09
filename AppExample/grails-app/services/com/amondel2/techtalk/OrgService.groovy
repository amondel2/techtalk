package com.amondel2.techtalk

import grails.transaction.Transactional

@Transactional
class OrgService {

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
}