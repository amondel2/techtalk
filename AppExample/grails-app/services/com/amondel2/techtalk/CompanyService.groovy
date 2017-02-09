package com.amondel2.techtalk

import grails.transaction.Transactional

@Transactional
class CompanyService {

    def orgService

    def getSubOrgs(companyId) {
        Organization.withCriteria{
            eq('company',Company.findById(companyId))
            projections {
                property('id')
            }
        }
    }
}
